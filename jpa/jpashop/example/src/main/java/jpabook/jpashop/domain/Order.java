package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //cascade 넣으면, 여기에 save하면, 알아서 persist(); 날아감. 이 프로젝트에 OrderItem과 Delivery는 Order 이외 다른곳 참조 안함. 여기가 유일함. 그래서 cascade줘도 위허하지 않다.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascade 넣으면, 여기에 save하면, 알아서 persist(); 날아감. 이 프로젝트에 OrderItem과 Delivery는 Order 이외 다른곳 참조 안함. 여기가 유일함. 그래서 cascade줘도 위허하지 않다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //status 표시를 위한 enum을 jpa에선 이런 어노테이션 사용
    private OrderStatus status;

    //Member:Order = 1:N 양방향일 떄, 주인인 Order에서 하인인 Member 더하기는 가능한데, 하인인 Member에서 주인인 Order더하긴 안되니까,(Order attribute에 null이 됨) 수동으로 더해주는 것.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); //주인에서 하인을 더할 수 있다.
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this); //Q. Order:OrderItem = 1:N 양방향일 떄, 주인인 OrderItem에서 하인인 Order 더하기는 가능하니까, 얜 필요 없는거 아닐까?
        //아니면 양방향 매핑 떄는, setter할 떄, 주인하인 상관없이 다른쪽도 더해주는 코드가 반드시 있어야 하나? 그러나 보다.
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // orderPrice를 따로 받는 이유는 할인이나 등급등으로 회원마다 다른 주문금액이 될 수 있기 때문이다.
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order(); //새 주문 만들어서
        order.setMember(member); //기존 멤버 정보랑
        order.setDelivery(delivery); //delivery 정보 넣고,

        for (OrderItem orderItem : orderItems) { //기존 주문한 상품 정보 넣고,
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); //order status 바꾸고,
        order.setOrderDate(LocalDateTime.now()); //시간 수정해서
        return order; //반환.
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

}
