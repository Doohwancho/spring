package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //다른 패키지에서 생성자 제한 막는다
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) { //이 파라미터 3개 넣는것 만으로 OrderItem 객체 생성가능
        OrderItem orderItem = new OrderItem();

        //무조건 아래 3개 필드 주입해야 객체 생성 가능
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //여기에 내가 원하는 기능도 추가 가능.
        item.removeStock(count);

        return orderItem;
    }

    //재고 수량을 원복한다
    public void cancel() {
        item.addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
