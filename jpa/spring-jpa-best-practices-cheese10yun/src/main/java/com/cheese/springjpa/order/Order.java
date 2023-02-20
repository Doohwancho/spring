package com.cheese.springjpa.order;

import com.cheese.springjpa.coupon.Coupon;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "price")
    private double price;

    //NOT NULL 제약 조건을 준수해서 안전성이 보장 + 외래 키에 NOT NULL 제약 조건을 설정하면 값이 있는 것을 보장합니다.
    //따라서 JPA는 이때 내부조인을 통해서 내부 조인 SQL을 만들어 주고 이것은 외부 조인보다 성능과 최적화에 더 좋습니다.
    @OneToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id", nullable = false)

    private Coupon coupon;

//    @OneToOne(mappedBy = "order")
//    private Coupon coupon;

    @Builder
    public Order(double price) {
        this.price = price;
    }

    public void applyCoupon(final Coupon coupon) {
        this.coupon = coupon;
        coupon.use(this);
        price -= coupon.getDiscountAmount();
    }

}
