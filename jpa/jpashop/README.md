---\
Index


A. entity modeling
B. jpql conditional 처리
C. Criteria



---\
A. entity modeling


a. 요구사항
- 회원 기능
    1. 회원 등록
    2. 회원 조회
- 상품 기능
    1. 상품 등록
    2. 상품 수정
    3. 상품 조회
- 주문 기능
    1. 상품 주문
    2. 주문 내역 조회
    3. 주문 취소
- 기타 요구사항
    1. 상품의 종류는 도서, 음반, 영화가 있다.
    2. 상품을 카테고리로 구분할 수 있다.
    3. 상품 주문 시 배송 정보를 입력할 수 있다.



b. rdb modeling - 논리 모델(UML)
![uml](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbZu2zt%2FbtrD1uuDxLf%2FOGiQS7kNMGyHFKZMSNCpo1%2Fimg.png)

- 회원, 주문, 상품의 관계: 회원은 여러 상품을 주문할 수 있다. 그리고 한 번 주문할 때 여러 상품을 선택할 수 있으므로 주문과 상품은 다대다 관계다. 하지만 이런 다대다 관계는 관계형 데이터베이스는 물론이고 엔티티에서도 거의 사용하지 않는다. 따라서 그림처럼 주문상품이라는 엔티티를 추가해서 다대다 관계를 일대다, 다대일 관계로 풀어냈다.
- 상품 분류: 상품은 도서, 음반, 영화로 구분되는데 상품이라는 공통 속성을 사용하므로 상속 구조로 표현했다.

![UML2](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqmIWB%2FbtrDVMKxPvu%2FzOcaTthx0tW2ClqwcKykk0%2Fimg.png)

- 회원(Member): 이름과 임베디드 타입인 주소(Address), 그리고 주문(orders) 리스트를 가진다.
- 주문(Order): 한 번 주문시 여러 상품을 주문할 수 있으므로 주문과 주문상품(OrderItem)은 일대다 관계다. 주문은 상품을 주문한 회원과 배송 정보, 주문 날짜, 주문 상태(status)를 가지고 있다. 주문 상태는 열거형을 사용했는데 주문(ORDER), 취소(CANCEL)를 표현할 수 있다.
- 주문상품(OrderItem): 주문한 상품 정보와 주문 금액(orderPrice), 주문 수량(count) 정보를 가지고있다. (보통 OrderLine, LineItem으로 많이 표현한다.)
- 상품(Item): 이름, 가격, 재고수량(stockQuantity)을 가지고 있다. 상품을 주문하면 재고수량이 줄어든다. 상품의 종류로는 도서, 음반, 영화가 있는데 각각은 사용하는 속성이 조금씩 다르다.
- 배송(Delivery): 주문시 하나의 배송 정보를 생성한다. 주문과 배송은 일대일 관계다.
- 카테고리(Category): 상품과 다대다 관계를 맺는다. parent, child로 부모, 자식 카테고리를 연결한다.
- 주소(Address): 값 타입(임베디드 타입)이다. 회원과 배송(Delivery)에서 사용한다.

c. rdb modeling - 물리 모델(table ERD)

![ERD](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQCn9o%2FbtrDYeNs1CK%2FMwz0KO8TcKFcpk2gsjkLy0%2Fimg.png)
- MEMBER: 회원 엔티티의 Address 임베디드 타입 정보가 회원 테이블에 그대로 들어갔다. 이것은 DELIVERY 테이블도 마찬가지다.
- ITEM: 앨범, 도서, 영화 타입을 통합해서 하나의 테이블로 만들었다. DTYPE 컬럼으로 타입을 구분한다.


d. entity modeling (연관관계)

- 회원과 주문: 일대다, 다대일의 양방향 관계이다. 따라서 연관관계의 주인을 정해야 하는데, 외래 키가 있는 주문을 연관관계의 주인으로 정하는 것이 좋다. 그러므로 Order.member를 ORDERS.MEMBER_ID 외래 키와 매핑한다.
- 주문상품과 주문: 다대일 양방향 관계이다. 외래 키가 주문상품에 있으므로 주문상품이 연관관계의 주인이다. 그러므로 OrderItem.order를 ORDER_ITEM.ORDER_ID 외래 키와 매핑한다.
- 주문상품과 상품: 다대일 단방향 관계이다. OrderItem.item을 ORDER_ITEM.ITEM_ID 외래 키와 매핑한다.
- 주문과 배송: 일대일 양방향 관계이다. Order.delivery를 ORDERS.DELIVERY_ID 외래 키와 매핑한다.
- 카테고리와 상품: @ManyToMany를 사용해서 매핑한다.(실무에서 @ManyToMany는 사용하지 말자. 여기서는 다대다 관계를 예제로 보여주기 위해 추가했을 뿐이다)



entity 설계시 주의점

1. Entity에는 가급적 Setter를 쓰지 않는다.
   - 변경 포인트가 많아지고 유지보수가 어려워진다. 스프링을 하며 많이 듣는 이야기 중 하나.
2. Enum을 쓴 땐, @Enumberated(EnumType.ORDINAL) vs @Enumberated(EnumType.STRING) 중, 무조건 후자를 쓴다. 
3. 모든 연관관계의 FetchType 은 LazyLoading 으로 설정한다.
   - 왜냐하면, EAGER(즉시로딩) 은 예측이 어렵고 어떤 SQL 이 실행될지 추적이 어렵기 때문.
   - @XToOne(OneToOne, ManyToOne) 의 경우 EAGER 가 default 이므로 직접 LAZY 로 설정해줘야 한다. (@XToMany 는 기본이 LAZY 라서 그냥 둬도 된다)
   - 만일 내가 연관관계에 필요한 entity 를 한번에 가져오고 싶은경우?  => FETCH JOIN 을 사용하자. EAGER 를 고려하지 말자.
4. Collection은 필드에서 초기화 하자!
 



---
B. JPQL conditional 처리


```java
public List<Order> findAllByString(OrderSearch orderSearch) {
    String jpql = "select o from Order o join o.member m"; //jpql에서는 조인을 이런식으로 하는구나. 이미 Member:Order = 1:N 양방향 관계인데, 이미 join맺어진 걸 가르키는 구나.

    boolean isFirstCondition = true;

    if (orderSearch.getOrderStatus() != null) {
        jpql += " where o.status = :status"; //아 조건에 부합하지 않으면, 이런식으로 jpql을 붙이네?
    }

    if (StringUtils.hasText(orderSearch.getMemberName())) {
        if (isFirstCondition) {
            jpql += " where";
            isFirstCondition = false;
        } else {
            jpql += " and";
        }

        jpql += " m.name like :name";
    }

    TypedQuery<Order> query = em.createQuery(jpql, Order.class)
            .setMaxResults(1000);

    if (orderSearch.getOrderStatus() != null) {
        query.setParameter("status", orderSearch.getOrderStatus());
    }

    if (StringUtils.hasText(orderSearch.getMemberName())) {
        query.setParameter("name", orderSearch.getMemberName());
    }

    return query.getResultList();
}
```

조건식에 맞춰서 String concat을 이용해 jpql문을 붙였다 띄었다 가능하네?
그리고 .setParameter()로 name을 jpql문에 :name으로 넣네?


---
C. Criteria


김영한 피셜, 현업에서는 Criteria 너무 비직관적/복잡해서 queryDSL 위주로 쓴다고 하는데,
그래도 어떻게 생겨먹었는지 인지할 순 있어야겠지?

```java 
public List<Order> findAllByCriteria(OrderSearch orderSearch) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> o = cq.from(Order.class);
    Join<Order, Member> m = o.join("member", JoinType.INNER);

    List<Predicate> criteria = new ArrayList<>();

    if (orderSearch.getOrderStatus() != null) {
        Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
        criteria.add(status);
    }

    if (orderSearch.getMemberName() != null) {
        Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
        criteria.add(name);
    }

    cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
    TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);

    return query.getResultList();
}
```

음.. 이것도 나름 가독성 좋으라고 깎고 깎은 코드일텐데,
조잡하게 보이는걸 보면, 왜 queryDSL 쓰는지 알 것 같네.
new Predicate[]는 또 뭐지? 객체 생성이 왜 ()가 아니고 []로 받지?



