package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //user-defined class도 jpa가 알아들을 수 있게 마크.
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    /**
     * Collection 은 필드에서 초기화 하는게 best-practice
     *
     * 그 이유는 다음과 같다
     *
     * 1. 객체의 초기화에 대한 고민을 하지 않아도 된다. (NPE 걱정 X)
     * 2. Hibernate 가 Entity 를 Persist 하는 순간 collection 을 감싸서 hibernate 용 내장 컬렉션으로 변경한다.
     *      Hibernate 가 collection 의 변경사항을 추적하기 위함인데,
     *      이것을 setter 등을 이용해서 new ArrayList<>(); 를 통해 초기화를 시켜버릴 경우 Hibernate 가 원하는 메커니즘으로 동작하지 않을 수 있다.
     */
}
