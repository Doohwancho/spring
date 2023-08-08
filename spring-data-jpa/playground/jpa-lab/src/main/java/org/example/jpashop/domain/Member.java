package org.example.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

@Entity
@Getter @Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@ToString(of = {"id", "userName", "age"}) //Member(주인):Team = N:1 양방향 관계에서, 양쪽에 toString()걸었을 때, 순환참조 문제 해결하려고 Team 빼고 적용
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;
    
    private int age;
    
    @Embedded //TODO - user-defined class도 jpa가 알아들을 수 있게 마크.
    private Address address;

    @OneToMany(mappedBy = "member") //TODO - Member : Order = 1 : N (양방향). 그래서 @OneToMany 붙여줌
    private List<Order> orders = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    
    
    /**
     * TODO - Collection 은 필드에서 초기화 하는게 best-practice
     *
     * 그 이유는 다음과 같다
     *
     * 1. 객체의 초기화에 대한 고민을 하지 않아도 된다. (NPE 걱정 X)
     * 2. Hibernate 가 Entity 를 Persist 하는 순간 collection 을 감싸서 hibernate 용 내장 컬렉션으로 변경한다.
     *      Hibernate 가 collection 의 변경사항을 추적하기 위함인데,
     *      이것을 setter 등을 이용해서 new ArrayList<>(); 를 통해 초기화를 시켜버릴 경우 Hibernate 가 원하는 메커니즘으로 동작하지 않을 수 있다.
     */

    public Member(String userName) {
        this(userName, 0);
    }
    
    public Member(String userName, int age) {
        this(userName, age, null);
    }
    
    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }
    
    public void changeTeam(Team team) { //주인 쪽에서 하인 더함
        this.team = team; //이 부부이 없으면 FK칸에 null이 뜬다.
        team.getMembers().add(this);
    }
}
