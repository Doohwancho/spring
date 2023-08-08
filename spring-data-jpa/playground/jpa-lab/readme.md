---\
요구사항

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


---\
rdb modeling - 논리 모델(UML)

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


---\
rdb modeling - 물리 모델(table ERD)

![ERD](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQCn9o%2FbtrDYeNs1CK%2FMwz0KO8TcKFcpk2gsjkLy0%2Fimg.png)
- MEMBER: 회원 엔티티의 Address 임베디드 타입 정보가 회원 테이블에 그대로 들어갔다. 이것은 DELIVERY 테이블도 마찬가지다.
- ITEM: 앨범, 도서, 영화 타입을 통합해서 하나의 테이블로 만들었다. DTYPE 컬럼으로 타입을 구분한다.


---\
Index


A. entity modeling은 딱 3개 하면 된다.\
B. 1. relations - erd에서 fk로 relations 맺은걸, jpa에선 어떻게 relations 맺지?\
C. 1. relations - Address나 OrderStatus같은 @Embeddable, @Enumerated는 별도 테이블이 만들어짐?\
D. 1. relations - Book extends Item, Movie extends Item 이런식으로 상속관계를 맺는건 어떻게 되는거지?\
E. 2. 단/양방향 - relations은 알겠는데, 단/양방향은 뭘 기준으로 맺음?\
F. 3. 주인 파악 - 주인은 뭘 기준으로 맺음?\
G. 3. 주인 파악 - order : delivery = 1:1, 양방향에서, 누가 주인인게 좋을까?\
H. jpashop에서 1. relaions && 2. 단/양방향 && 3. 주인 파악하기\
I. entity 설계시 주의점



---\
A. entity modeling은 딱 3개 하면 된다.


1. relations 파악 (ex. 1:1, N:1, etc)
2. 단/양방향 파악 (단방향에선 주인만 하인 볼 수 있고, 하인은 주인 못봄. 양방향에선 주인은 fk create, update, delete 가능한데, 하인은 fk에 대해 read만 가능함)
3. 주인 파악 (주로 N쪽, fk 관리하는 놈이 주인이다)



---\
B. 1. relations - erd에서 fk로 relations 맺은걸, jpa에선 어떻게 relations 맺지?\



A. 그냥 필드로 선언하면, erd처럼 member_id라는 이름의 long type 필드가 있으면, 그걸 fk 처럼 query 에 수동으로 적는식으로 할 수는 있는데,

근데 그럴꺼면 그냥 sql 썼지.

ex. Member:Order = 1:N
```java
@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Long member_id;
    
    private LocalDateTime orderDate;
    
    //...
}
```

이런식으로 하면 erd 그린 것 처럼 order table에 member_id가 있긴 한데,
이렇게 안하고, orm은 객체들을 referencing 하는 식으로 해서 relations을 맺음.

ex. Member:Order = 1:N
```java
@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    // ...
```

jpa에서는 이런 식으로 엔티티, 객체 끼리 relations을 맺는다.


---\
C. 1. relations - Address나 OrderStatus같은 @Embeddable, @Enumerated는 별도 테이블이 만들어짐?\


A. DeliveryStatus, OrderStatus 같은 @Enumerated(enum)이나, Address같은 @Embedded는 별도의 테이블이 생성되지 않고,\
참조한 테이블에 통합되어, db에 테이블로 만들어진다.

아 그럼 erd 그릴 때, 고대로 db에 테이블 어떻게 설계할 까가 되는게 아니고,\
erd 참고해서 엔티티 모델링을 하면, 그게 진짜 디비에 테이블로 만들어지는거네


---\
D. 1. relations - Book extends Item, Movie extends Item 이런식으로 상속관계를 맺는건 어떻게 되는거지?\


Book, Album, Movie의 필드가 모두 item에 떄려박게됨.
h2에 item 테이블 하나만 생기고, book, album, movie 테이블은 없음.

테이블마다 따로 빼는서 매번 query할 떄마다 join하는 방식 보다 빠르긴 한데,
문제가 Book 객체 만들 떄 Album, Movie에 쓰이는 필드는 다 null값이 들어가고, 다른 Album이나 Movie 객체 만들 떄 Book에 쓰이는 필드는 다 null값이 들어가는 문제가 있음.
database 공간이 비효율적이겠지?

그래서 보통은 상속관계 맺는건 피하고, 그냥 각각의 테이블을 따로 만들거나,
option table을 따로 만들어서, 그걸 참조하는 식으로 한다.


---\
E. 2. 단/양방향 - relations은 알겠는데, 단/양방향은 뭘 기준으로 맺음?\


member : order(주인) = 1 : N, 양방향

Q. 왜 양방향?

A. 만약 단방향이 었다면, N쪽, 주인인 order에서밖에 member 조회 못함.
근데 requirements에서 유저는 자신의 주문 목록을 조회할 수 있어야 한다매.
그러면 하인인 member에서 주인인 order를 조회가능해야 할거아냐?
그러니까 양방향 잡은 것.


orderItem : Item = N : 1, 단방향

Q. 이건 왜 단방향?

item에서 orderItem을 조회할 일이 없잖아.

예를들어, 아이폰 테이블에서, 아이폰 주문 목록을 조회할 일이 없잖아.




---\
F. 3. 주인 파악 - 주인은 뭘 기준으로 맺음?\

a. 단방향인 경우

일단 단방향이면 주인 걱정할 필요 없음. 항상 fk가 있는 곳이 주인이다.

주인(owner)라는 이름 때문에 오해가 있을 수 있는데,
보통 비즈니스 로직상 더 중요한 것 같은 애를 주인으로 정해야 할 것 같은데,
사실은 비즈니스 중요도 배제하고 외래키의 '관리자'를 owner(주인)으로 정해야 한다.

예를들어, car : wheel = 1 : N 단방향인 경우,
비즈니스상 car이 더 중요하게 느껴지긴 하나,
fk를 가지고 관리하는 N 쪽인 wheel이 주인이다.


ex. member(주인) : team = N : 1, 단방향

```java
@Entity 
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    
    // ...
}
```

N쪽인 member가 team의 fk를 받아와 관리하니까, member가 주인이다.

member에서 member.getTeam()으로 team을 가져올 수 있는데,
team에서는 member 필드가 업성서 team.getMembers()로 member를 가져올 수 없다.

@JoinColumn()을 쓰는 쪽이 주인이다.


```java
@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;
    
    // ...
}
```

그리고 member 에서 team을 insert하는 코드는 다음과 같다.

```java
public void test(){
    Team team = new Team();
    team.setName("teamA");
    em.persist(team);

    Member member = new Member();
    member.setUsername("member1");
    member.setTeam(team);
    em.persist(member);
}
```

member.setTeam(team); 넣어주고,
em.persist(member); 해주면 영속성 컨텍스트에 올라가는 듯?



jpashop에서 orderItem : item = N : 1, 단방향이니 참고.
orderItem에서는 item을 무조건 참조 가능해야 하는데,
item에서 굳이 orderItem을 참조할 이유가 없으니까 (order에서 orderItem 참조하니까 보통) 단방향으로 짠 듯.
나머지는 왠만하면 양방향으로 구현되어있다.





b. 양방향인 경우

양방향은 사실 서로 다른 두개의 단방향을 합친것이다.

양방향일 떄, 연관관계의 주인을 정해야 하는데, "대부분의 경우" 외래 키가 있는 주문을 연관관계의 주인으로 정하는 것이 좋다.

ex.
jpashop에서 member : order(주인) = 1 : N 양방향,\
order : orderItem(주인) = 1 : N 양방향\
등, 양방향인 경우가 많다.

왜냐하면, member : order = 1 : N을 보면,\
order이 member의 fk를 가지고 있기 때문에(N쪽) 주인으로써 order.getMember()로 접근 가능한데,\
member에서도 주문목록 보고싶으면, member.getOrders()로 접근 가능해야 하니까, 양방향으로 처리한 듯?


example)
member : order(주인) = 1 : N, 양방향 코드 예제


```java
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

    //Member:Order = 1:N 양방향일 떄, 주인인 Order에서 하인인 Member 더하기는 가능한데, 하인인 Member에서 주인인 Order더하긴 안되니까,(Order attribute에 null이 됨) 수동으로 더해주는 것.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); //주인에서 하인을 더할 수 있다.
    }
}
```

1. member : order(주인) = 1 : N, 양방향에서 order가 주인이니까, @JoinColumn()을 썼다.
2. 주인 쪽에서 setMember()시 하인인 member의 List<Orders>에 가서 member.getOrders().add(this)로 추가해준다.
    3. why? -> 주인만 해당 필드에 create, update, delete이 가능하지, 하인은 그냥 읽기만 가능하다.
    4. 그래서, 주인에서 하인을 더해주는 것이다. 하인에서 주인을 더하려고 하면, 주인의 필드가 null이 되기 때문에 안된다.

양방향 매핑 시, 이 점을 꼭 숙지해야 한다.



```java
@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //user-defined class도 jpa가 알아들을 수 있게 마크.
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
```

1. 하인은 mappedBy를 써서 주인을 지정해 주어야 한다.
2. member : orders(주인) = 1 : N 이기 때문에, order들을 list에 담아준다. 나중에 member.getOrders()로 접근 가능하다.


h2-console에서 확인해보면, order에는 member_id 필드가 있는데,\
member에는 order_id 필드가 없다.

왜냐하면, 하인인 member에서 주인인 order를 더해주는 것이 아니라, 주인인 order에서 하인인 member를 더해주기 때문이다.
주인이 fk를 관리하기 때문에, 주인만 fk에 대해 create, update, delete 할 수 있고,
하인은 read만 가능하다.

예를들어, member.getOrders().add(order);를 하면, 무시된다.
왜? member는 연관관계의 주인이 아니라, fk인 member.orders에 create/update/delete할 권한이 없고, 오직 read만 할 수 있기 때문.

반면, 주인인 order.setMember(member);를 하면, 위에도 적어놨는데,
member.getOrders().add(this); 로 member.orders에 추가해준다.



---\
G. 3. 주인 파악 - order : delivery = 1:1, 양방향에서, 누가 주인인게 좋을까?


1:1은 어느쪽을 주인 주는게 좋을까?
더 자주 참조하는 쪽이 주인인게 더 좋다.
order -> delivery 더 자주 참조하니까, order가 주인이 되는게 좋다.




---\
H. jpashop의 1. 연관관계(ex. 1:N) && 2. 단/양방향 && 3. 주인 파악하기\


1. 회원과 주문
    - member : order(주인) = 1 : N
    - 양방향 관계
2. 주문상품과 주문
    - orderItem(주인) : order = N : 1
    - 양방향 관계
3. 주문상품과 상품
    - orderItem : item = N : 1
    - 단방향 관계
        - 아이폰에서 아이폰 주문목록 조회할 필요 없잖아? 그래서 단방향임.
4. 주문과 배송
    - order(주인) : delivery = 1 : 1
    - 양방향 관계
    - 주인관계는?
        - order이 delivery의 fk를 가지고 있기 때문에, order가 주인이다.
5. 카테고리와 상품
    - category(주인) : item = N : N
        - @ManyToMany를 사용해서 매핑한다.
        - (실무에서 @ManyToMany는 사용하지 말자. 여기서는 다대다 관계를 예제로 보여주기 위해 추가했을 뿐이다)
    - 양방향 관계
    - 주인관계는?
        - category가 item의 fk를 가지고 있기 때문에, category가 주인이다.


---\
I. entity 설계시 주의점

1. Entity에는 가급적 Setter를 쓰지 않는다.
    - 변경 포인트가 많아지고 유지보수가 어려워진다. 스프링을 하며 많이 듣는 이야기 중 하나.
2. Enum을 쓴 땐, @Enumberated(EnumType.ORDINAL) vs @Enumberated(EnumType.STRING) 중, 무조건 후자를 쓴다.
3. 모든 연관관계의 FetchType 은 LazyLoading 으로 설정한다.
    - 왜냐하면, EAGER(즉시로딩) 은 예측이 어렵고 어떤 SQL 이 실행될지 추적이 어렵기 때문.
    - @XToOne(OneToOne, ManyToOne) 의 경우 EAGER 가 default 이므로 직접 LAZY 로 설정해줘야 한다. (@XToMany 는 기본이 LAZY 라서 그냥 둬도 된다)
    - 만일 내가 연관관계에 필요한 entity 를 한번에 가져오고 싶은경우?  => FETCH JOIN 을 사용하자. EAGER 를 고려하지 말자.
4. Collection은 필드에서 초기화 하자!


