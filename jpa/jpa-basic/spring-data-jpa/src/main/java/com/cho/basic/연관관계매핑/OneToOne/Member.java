package com.cho.basic.연관관계매핑.OneToOne;

import lombok.*;

import javax.persistence.*;

@Entity
//JPA는 이 객체의 필드와 애노테이션을 기반으로 DDL을 작성하고 DB는 해당 DDL을 통해 테이블을 생성해준다.
//@Entity 붙이기 위한 3가지 조건
//1. 기본 생성자가 꼭 있어야 한다.
@NoArgsConstructor
//2. final, interface, enum, inner 클래스가 아닌 기본 클래스여야 한다.
//3. 저장할 프로퍼티에 final을 사용할 수 없다.

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING) //타입이 클래스가 아닌 enum일 때 사용. 만약, @Enumerated(EnumType.ORDINAL)을 사용하면 그냥 정수값인 0, 1, 2 값으로 들어가게 된다. 보통은 STRING 쓴다.
    private LoginType loginType;

    /**
     * 연관관계. @OneToOne
     *
     * 1. @JoinColumn은 관계의 주인이 갖는다. (select * from member join MyPage) 일 때, member쪽이 갖는다는 뜻인 듯.
     * 2.. "MYPAGE_ID" 로 매핑했는데, MyPage.java에서는
     *     @Id
     *     private Long Id;
     *
     *     이렇게 밖에 안되있음. 그냥 ID가 내부적으로 네이밍이 저렇게 되있는 듯.(Entity명 + _ + ID)
     */
    @OneToOne
    @JoinColumn(name = "MyPage_ID")
    private MyPage myPage;
}
