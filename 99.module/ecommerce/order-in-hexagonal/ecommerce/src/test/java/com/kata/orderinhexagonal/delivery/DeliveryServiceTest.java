package com.kata.orderinhexagonal.delivery;

import com.kata.orderinhexagonal.delivery.adapter.out.persistence.DeliveryEntity;
import com.kata.orderinhexagonal.delivery.adapter.out.persistence.OrderDeliveryEntity;
import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import com.kata.orderinhexagonal.delivery.application.service.DeliveryService;
import com.kata.orderinhexagonal.delivery.domain.Delivery;
import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import com.kata.orderinhexagonal.fixture.DeliveryFixture;
import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class DeliveryServiceTest extends TestConfig {

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    OrderFixture orderFixture;

    @Autowired
    DeliveryFixture deliveryFixture;

    @Autowired
    DeliveryService deliveryService;


    //TODO - c-b-7-4. deliveryServiceTest시 order이 다른 테스트와 충돌하는 문제 어떻게 해결?
    @Test
    void 배송정보_저장() throws InterruptedException {
        // given
        Member member = memberFixture.createMember("user1", "hahaha@gmail.com", "seoul");
        Order order = orderFixture.createOrder(member.getId());
        DeliveryStatus deliveryStatus = DeliveryStatus.SHIPPING;
        String location = "야시장";
        DeliveryRequest request = new DeliveryRequest(member.getId(), deliveryStatus, location);

        // when
        Delivery result = deliveryService.create(request);

        // then
        Assertions.assertThat(result.getId()).isPositive();
        Assertions.assertThat(result.getStatus()).isEqualTo(deliveryStatus);
        Assertions.assertThat(result.getLocation()).isEqualTo(location);
        DeliveryEntity deliveryEntity = deliveryFixture.getDeliveryEntity(result.getId());
        Assertions.assertThat(deliveryEntity.getId()).isEqualTo(result.getId());
        Assertions.assertThat(deliveryEntity.getStatus()).isEqualTo(result.getStatus());
        Assertions.assertThat(deliveryEntity.getLocation()).isEqualTo(result.getLocation());
        List<OrderDeliveryEntity> orderDeliveryList =  deliveryFixture.getOrderDeliveryList(order.getId());
        OrderDeliveryEntity orderDelivery = orderDeliveryList.get(0);
        Assertions.assertThat(orderDelivery.getOrder().getId()).isEqualTo(order.getId());
        Assertions.assertThat(orderDelivery.getDelivery().getId()).isEqualTo(result.getId());
    }



    //TODO - c-b-7-4. deliveryServiceTest시 order이 다른 테스트와 충돌하는 문제 어떻게 해결?
    @Test
    // @Transactional  // 붙이면 에러난다! 이유는 아래 적혀있다.
    void 배송정보_여러번_저장() {
        // given
        Member member = memberFixture.createMember("user2", "hahaha@gmail.com", "seoul");
        Order order = orderFixture.createOrder(member.getId());

        DeliveryRequest request1 = new DeliveryRequest(member.getId(), DeliveryStatus.SHIPPING, "야시장");
        DeliveryRequest request2 = new DeliveryRequest(member.getId(), DeliveryStatus.DELIVERED, "수산시장");

        // when
        deliveryService.create(request1);
        deliveryService.create(request2);

        // then
        List<OrderDeliveryEntity> orderDeliveryList = deliveryFixture.getOrderDeliveryList(order.getId());
        Assertions.assertThat(orderDeliveryList).hasSize(2);
        Assertions.assertThat(orderDeliveryList.get(0).getDelivery().getStatus()).isEqualTo(DeliveryStatus.SHIPPING);
        Assertions.assertThat(orderDeliveryList.get(1).getDelivery().getStatus()).isEqualTo(DeliveryStatus.DELIVERED);
        Assertions.assertThat(orderDeliveryList.get(0).getOrder()).isEqualTo(orderDeliveryList.get(1).getOrder()); //TODO - c-b-7-3. commit() 이전 1차 캐시에서 OrderEntity 객체 주솟값 비교
        // Q. 왜 transactional 붙이면 다른 주솟값 나와서 오히려 실패하지?

        // 예상
        // 트랜젝션 어노테이션 없으면, service 내부 CreateDeliveryAdapter.save()에 붙어있는 @Transactional이 개별 작동되어, db에 커밋 함.
        // 이후, deliveryFixture.getOrderDeliveryList(order.getId())로 db에서 가져올 때, (.find())
        // db에서 1차 캐시로 영속화 되는 과정에서, OrderEntity가 같은 주소값을 가르키게 됨.

        // 반면, 테스트에 @Transactional을 붙이면, CreateDeliveryAdapter.save() 에 @Transactional이 붙어있어도, 아직 부모 트랜젝션이 안끝났기 때문에, db에 commit()을 아직 안한 상황.
        // 아직 1차 캐시에 OrderEntity 객체가 각각 올라가 있는 상황
        // 따라서 OrderEntity의 주솟값이 다른 것.
    }
}