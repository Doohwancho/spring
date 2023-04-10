---\
Index


1. relations
2. 단/양방향
3. 주인

.. on

A. MemberEntity
B. ItemEntity
C. StockEntity
D. DiscountEntity
E. OrderItemEntity
F. OrderEntity
G. DeliveryEntity
H. PaymentEntity


---\
A. MemberEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String location;
}
```


---\
B. ItemEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;
    
	@Column(nullable = false)
	private Integer stockQuantity;

	@OneToOne(mappedBy = "item", fetch = FetchType.LAZY)
	private DiscountEntity discount;
}
```

---\
C. DiscountEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private DiscountType discountType;


	@OneToOne(fetch = javax.persistence.FetchType.LAZY) //? - discount : item = N : 1 이지 않나?
	@JoinColumn(name = "item_id")
	private ItemEntity item;

	private int discountValue;
}
```

---\
C. StockEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)e
	private Long id;

	private Integer quantity;

	@Enumerated(EnumType.STRING)
	private Stock.StockType stockType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;
}
```

---\
E. OrderItemEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)e
	@Column(name = "order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private ItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@Column(nullable = false)
	private Integer orderPrice;

	@Column(nullable = false)
	private Integer orderQuantity;
}
```

---\
F. OrderEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberEntity member;

	@OneToMany(mappedBy = "order", orphanRemoval = true)
	List<OrderDeliveryEntity> orderDeliveries = new ArrayList<>();
}
```

---\
G. DeliveryEntity


```java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DeliveryStatus status;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDateTime;
}
```

---\
H. PaymentEntity


```java
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = javax.persistence.FetchType.LAZY)
	@JoinColumn(name = "order_id", unique = true, nullable = false)
	private OrderEntity order;

	@Column(nullable = false)
	private String cardNumber;

	@Column(nullable = false)
	private String cardCvc;

	@Column(nullable = false)
	private Integer paymentPrice;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	private CardType cardType;

	@Enumerated(EnumType.STRING)
	private CardCompany cardCompany;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
}
```
