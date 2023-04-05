


---
MemberEntity.java


```java
@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String authId;
    private String authPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
    private String phone;

    @Embedded
    private Address address;
}
```

---
BaseEntity.java

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
```

얜 date 상속용 엔티티.


---
Role.java

```java
@Getter
public enum Role {
    USER("USER"), ADMIN("ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }
}
```

Q. enum Role does not have annotation @Entity. therefore, it will not be treated as entity. correct? if so, how does it treated in sql?

A. 
That's correct. The Role enum is not an entity, and it doesn't require the @Entity annotation. Enums in Java are a special kind of class that define a fixed set of named constants. They are not treated as entities in JPA, and no separate table will be created for them in the database.

In your MemberEntity class, you have used the @Enumerated(EnumType.STRING) annotation for the role field. This means that when JPA persists the MemberEntity object to the database, the role field will be stored as a string (VARCHAR) representing the name of the Role enum constant (e.g., "USER" or "ADMIN"). When JPA retrieves the data from the database, it will automatically map the string back to the corresponding enum constant in the Role enum.

In summary, the Role enum will not be treated as an entity and will not have its own table in the database. Instead, its string representation will be stored in the "members" table as a column, and JPA will handle the conversion between the enum constants and their string representation when persisting and retrieving data.



---
Address.java


```java
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    private String city;
    private String street;
}
```

Q. what about Address.java? it does not have @Entity annotation. therefore, its not treated as entity, nor does it create separate table named 'Address'. am I correct? if so, does Address treated like enum just like 'Role' you explain?

A. 
Yes, you are correct that the Address class does not have the @Entity annotation and is not treated as an entity. However, unlike the Role enum, the Address class is annotated with @Embeddable. This means it is an embeddable class, which is a different concept from enums.

An embeddable class is a class whose instances are stored as an intrinsic part of an owning entity and share the entity's identity. In this case, the Address class is embedded within the MemberEntity class. The fields of the Address class will be stored as columns in the table of the owning entity, which is the "members" table in your case.

An embeddable class does not have its own table in the database, and its lifecycle is bound to the lifecycle of the owning entity. The main difference between enums and embeddable classes is that enums define a fixed set of named constants, while embeddable classes represent a group of related fields that can be reused across multiple entities.

To summarize, the Address class is an embeddable class and will not be treated as an entity or have its own table in the database. Instead, its fields will be stored as columns in the owning entity's table (the "members" table), and JPA will handle the mapping of the fields when persisting and retrieving data.




---
ItemEntity.java


```java
@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    private Long categoryId;
}
```


Q. stock quantity를 OrderItemEntity에 보관해야하는거 아냐? 

A. 아 OrderItemEntity에 있는 orderCount가 주문수량, 전체 stock에서 까는 quantity 같은 개념이고,
전체 stock은 product에서 보관하는게 맞은 듯.


Q. 근데 왜 categoryId랑 ManyToOne relations 안맺지?

Q. why ItemEntity.java does not have relations with CategoryEntity.java? shouldn't it be ItemEntity : CategoryEntity = N : 1 relations? if so, 'categoryId' field in ItemEntity should be marked with @ManyToOne annotations, am I correct?

you are correct. implement code below.

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private CategoryEntity category;
```




---
CategoryEntity.java


```java
@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    

    private Long parentId;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> children = new ArrayList<>();
    */
}
```


Q. 왜 parentId에 셀프로 OneToMany 안붙이지?

A. 그래서 저렇게 하라고 하는데..
This structure will create a hierarchical relationship between categories, allowing you to navigate and manage the parent-child relationships between categories in your application.

음.. 이렇게 하는게 원래 방법보다 더 나을까?



---
OrderItemEntity.java


```java
@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    private int orderCount;
    private int orderItemAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
}
```



---
OrderEntity.java


```java
@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private int totalAmount;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private boolean removed;
    private LocalDateTime removedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity orderer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity deliveryInformation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();
}
```

Q. why user : order = 1 : 1? shouldn't it be 1: N relations? because a user can have multiple orders

A. You're correct that a user can have multiple orders, so the relationship between User and Order should be 1:N. To establish this relationship, you can modify the OrderEntity and MemberEntity classes like this:

In OrderEntity.java, change the @OneToOne annotation to @ManyToOne:

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "member_id")
private MemberEntity orderer;
```
In MemberEntity.java, add a new field orders with a @OneToMany relationship:

```java
@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
    // ...

    @OneToMany(mappedBy = "orderer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();
}
```




Q. OrderEntity : OrderItemEntity = 1 : N 라서 OrderEntity에서 @OneToMany 쓴거같은데, 
왠만하면 @ManyToOne 쓰는게 성능상 더 좋다고 들었는데?





---
DeliveryEntity.java


```java
@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long deliveryId;
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;
}
```


---
DeliveryStatus.enum

```java
@Embeddable
public enum DeliveryStatus {
    READY_STATUS, SHIPPING_STATUS, COMPLETE_STATUS
}
```




