package com.kata.orderinhexagonal.order.application.service;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.CancelOrderUsecase;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import com.kata.orderinhexagonal.order.application.port.out.*;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Timer;
import java.util.concurrent.Flow;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUsecase, CancelOrderUsecase {

    //TODO - c-b-6-3. Q. Member의 service를 주입받아도 되나? 아니면 port를 주입받아야 하나? (의존성 문제)
    //A. 여기서 다른 모듈의 서비스 주입받지 말고, adapter.out.persistence까지 가서 port로 다른 모듈의 repository를 주입받으면 된다.
    private final LoadOrdererPort loadOrdererPort;

    //TODO - c-b-6-5. Q. 왜 final 붙일까?
    //A. 1. 불변성 보장
    //
    // final 키워드를 사용하면 해당 필드가 불변성을 가진다는 것을 명시할 수 있습니다.
    // 이는 필드 값을 변경할 수 없다는 것을 의미하며, 코드의 안정성과 안정성을 높입니다.
    // Spring에서는 다양한 쓰레드에서 동시에 접근하는 경우가 많기 때문에, 필드 값이 변경되면 예기치 않은 동작이 발생할 수 있습니다.
    // 그래서 final을 사용하여 불변성을 보장하는 것이 좋습니다.

    //A. 2. 코드 가독성
    //final 필드를 사용하면 해당 필드가 변경되지 않는다는 것을 명시할 수 있습니다.
    // 이는 코드 가독성을 높이고, 코드를 이해하는 데 도움이 됩니다.
    // 코드에서 final 필드를 사용하면 해당 필드가 변경될 가능성이 없다는 것을 알 수 있기 때문에, 코드를 이해하기 쉬워집니다.
    private final LoadOrderItemPort loadOrderItemPort;
    private final ItemStockOutPort itemStockOutPort;
    private final SaveOrderPort saveOrderPort;
    private final LoadOrderPort loadOrderPort;
//    private final CancelPaymentPort cancelPaymentPort;
    private final CancelStockOutItemPort cancelStockOutItemPort;
    private final CancelOrderPort cancelOrderPort;


    @Transactional //TODO - Q. transactional 붙어야..겠지? 중간에 exception이 발생하면 어떻게 처리하지?
    public Order createOrder(CreateOrderRequest request) {
        //step1. 주문자의 ID를 통해 주문자 정보가 db에 있는지 확인하고, 없으면 throw no member exception
        Member orderer = loadOrdererPort.load(request.getOrdererId());

        //step2. create order
        Order order = new Order(orderer);

        //step3. itemID으로 item을 찾아 discount된 가격을 고려해 order에 item을 더해준다.
        request.getOrderItemRequests().forEach(orderItemRequest -> {
            //a. itemID로 item을 찾는다.
            Item item = loadOrderItemPort.load(orderItemRequest.getItemId());

            //b. item의 discount를 고려한 price를 구한다.
            //TODO - c-b-6-6. refactor - discounted price of item using switch
            int orderPrice = order.calculateDiscountedPrice(item);

            //c. order.addOrderItem();
            order.addOrderItem(item, orderItemRequest.getOrderQuantity(), orderPrice);
        });

        //step4. order.orderItems를 돌면서 request에 있던 수량 만큼 stockOut() 한다.
        order.getOrderItems().forEach(orderItem -> {
            //TODO - c-b-6-4. Q. item에서 stockOut()하면, domain에서 service로 의존성 생기지 않나? 그러면 hexagonal의 원칙에 어긋나지 않나?
            //orderItem.getItem().stockOut(orderItem.getOrderQuantity());
            //Q. 의존성 방향 지키면서 어떻게 stockOut()하지?

            //A. 역시 Port를 이용하자.
            itemStockOutPort.stockOut(orderItem.getItem(), orderItem.getOrderQuantity()); //stockOut요청을 queue에 async로 넣는가보다.
        });

        //step5. save
        saveOrderPort.save(order);

        //step6. return order
        return order;
    }

    @Transactional
    public Order cancelOrder(CancelOrderRequest request) {
        //step1. 주문자의 ID를 통해 주문자 정보가 db에 있는지 확인하고, 없으면 throw no member exception
        Member cancelRequester = loadOrdererPort.load(request.getOrdererId());

        //step2. 주문자가 주문 취소 요청한 사람과 일치하지 않으면 throw exception
        Order order = loadOrderPort.loadOrder(request.getOrderId());
        if(!order.isOrdererAndRequesterMatch(cancelRequester)){
            throw new IllegalStateException("주문한 사람과 주문 취소 요청자가 일치하지 않습니다.");
        }

        //step3. 주문이 이미 취소된 상품이면 throw exception (중복 요청 방지)
        if(order.isCanceled()){
            throw new IllegalStateException("이미 취소된 상품입니다.");
        }

        //step4. 주문이 이미 배송된 상품이면 throw exception
        if(order.isDelivered()){
            throw new IllegalStateException("이미 배송된 상품은 취소할 수 없습니다.");
        }

        //step5. 주문이 이미 결제된 상품이면 주문 취소 요청을 payment에 보낸다.
//        if(order.isPayed()){
//            cancelPaymentPort.request(order);
//        }

        //step6. 주문을 삭제하기 전, 주문 하위에 orderItem들의 주문 수량만큼 기존 Item의 quantity를 다시 복구시켜준다.
        order.getOrderItems().forEach(orderItem -> {
            cancelStockOutItemPort.cancelStockOutItem(orderItem.getItem(), orderItem.getOrderQuantity());
        });

        //step7. 주문을 삭제한다.
        cancelOrderPort.cancel(order);

        return order;
    }
}
