---\
Index


A. requirements -> entity modeling\
B. jpql conditional 처리\
C. Criteria\
D. java generics 활용 on Controller\
E. flush()\
F. controller에서 파라미터로 받은 객체는 영속성 객체가 아니다\
G. update시 팁: merge 보다는 find() 후 setter 하고 냅둬



---\
A. requirements -> entity modeling

1. 요구사항
   1. ./documentation/requirements.md
2. UML
   2. ./documentation/uml.md
3. ERD modeling
   3. ./documentation/erd.md 
   4. ./documentation/erd.vuerd.json (VSC - Vuerd extension)
4. entity modeling
   5. ./documentation/entity-modeling.md



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



---
D. java generics on Controller layer 

MemberApiController.java에서, List<Member> 결과 값 과, 사이즈를 한 객체에 담아 반환을 generic을 이용해 처리함.
```java
@GetMapping("/api/v1/members")
public List<Member> membersV1() {
    return memberService.findMembers();
}

@GetMapping("/api/v2/members")
public Result membersV2() {
    List<Member> findMembers = memberService.findMembers();
    List<MemberDto> collect = findMembers.stream()
            .map(m -> new MemberDto(m.getName()))
            .collect(Collectors.toList());

    return new Result(collect.size(), collect);
}

@Data
@AllArgsConstructor
static class Result<T> {
    private int count;
    private T data;
}
```



---
E. flush()


Q. MemberServiceTest.java에서, 그냥 Long savedId = memberService.join(member); 만 하면 되는데, em.flush(); 도 따로 해주네, 왜지?

A. write-behind에 sql문 담기고 아직 db에 안쏜 상태 예방하려고구나.

flush() synchronize the persistence context to the underlying database. It forces any pending changes to be written to the database immediately and also flushes the EntityManager's internal cache.

그래야 그 다음 코드인 assertEquals(member, memberRepository.findOne(savedId)); 을 했을 때, db에서 실제 값이 있어야 땡겨올 수 있지.

---
F. controller에서 파라미터로 받은 객체는 영속성 객체가 아니다.


ItemController.java
```java
@PostMapping("/items/{itemId}/edit")
public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {

    Book book = new Book();
    book.setId(form.getId());
    book.setName(form.getName());
    book.setPrice(form.getPrice());
    book.setStockQuantity(form.getStockQuantity());
    book.setAuthor(form.getAuthor());
    book.setIsbn(form.getIsbn());

    itemService.saveItem(book);

    return "redirect:/items";
}
```

- Book객체랑 똑같은 attribute 가지고 있는 BookForm 객체를 받아도,
얘를 고대로 itemService.saveItem(bookForm); 하면, 영속성 객체 아니라고, detached 객체 넣는다고 에러남!
- 따라서 new Book();으로 영속성 entity인 객체를 새롭게 만들어서 db에 넣어야 함.  


---
G. update시 팁: merge 보다는 find() 후 setter 하고 냅둬


MemberService.java에서,

```java
@Transactional
void update(Member param) {
    Member member = em.merge(param);
}
```

해도 가능은 한데, 비추인 이유

1. merge() 메서드는 파라미터로 받은 준영속 엔티티의 id(식별자) 값으로 엔티티를 조회한다.
   만일, 영속성 컨텍스트에서 찾았다면 해당 엔티티를 반환하고 없으면 DB에서 해당 식별자로 조회해온 후 영속성 컨텍스트에 올린 후 반환한다.
2. 조회한 영속 엔티티를 준영속 엔티티 값으로 모두 교체(병합) 
   1. 여기가 문제가 되는 지점
   2. 원하는 필드만 바꿀 수 있는게 아니라 모든 속성이 준영속 엔티티 값으로 교체됨
   3. 만일 정책상 상품 이름은 등록 후 수정이 안된다고 할 때 수정 필드가 없을텐데 그러면 필드에서 받은 값으로 만든 준영속 상태 엔티티는 name 이 비어있을 것. 
   4. 그러면 영속 상태의 엔티티도 null로 업데이트 쳐버린다.
3. 영속 상태인 병합된 엔티티를 반환


그러니까, update시, merge() 하지 말고,

MemberService.java
```java
@Transactional
public void update(Long id, String name) {
    Member member = memberRepository.findOne(id);
    member.setName(name);
}
```

find로 영속성 컨텍스트에서 entity 조회 후, setter로 원하는 필드만 바꾸면, transaction이 끝나는 시점에 flush()됨.

그리고 이 방법을 쓰면, controller -> service 단으로 Member 객체 정보 다 담을 필요 없고, DTO에 MemberId랑 업데이트 필요한 attribute만 담아 보내면 됨.




