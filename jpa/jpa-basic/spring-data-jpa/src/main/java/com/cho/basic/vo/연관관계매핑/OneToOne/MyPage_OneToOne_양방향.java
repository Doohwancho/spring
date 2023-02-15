package com.cho.basic.vo.연관관계매핑.OneToOne;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

//@OneToOne 관계, (Member.java)
//Member : MyPage = 1 : 1
//왜?
//멤버 1명당 필연적으로 1개의 마이페이지 가지고 있으니까.
@Entity
@Builder
@Getter
public class MyPage_OneToOne_양방향 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    /**
     * 양방향 1대1
     *
     * 하인 객체에 주인객체를 컬럼으로 넣어주면 된다. 즉 단방향을 추가해줌으로서 양방향이 된다.
     * 단, 이때 주인이 아닌 객체는 mappedBy를 통해 관계의 주인이 상대편임을 명시해줘야 한다.
     *
     * 참고사항
     * Member(주인):MyPage(하인) = 1:1
     * 일대일 관계에서 단방향이 아닌 양방향을 하게되면,
     * 주인인 Member 테이블 조회시, 하인 MyPage에 대한 Lazy Loading이 동작하지만,
     * 하인 MyPage 테이블 조회시, Lazy Loading이 작동하지 않는다. (N+1 problem 문제 발생)
     * 왜냐하면, 하인 테이블 조회 시, private Member_OneToOne_단방향 member; 이 멤버 정보를 가져오기 위해서, 반드시 주인 테이블을 조회해야 하기 때문.
     * 따라서 하인 테이블 조회시 Fetch join을 쓰거나 단방향으로 수정해서 Lazy loading이 되도록 해야한다.
     */
    @OneToOne(mappedBy = "myPage_oneToOne_양방향") //mappedBy의 인자로 주인에서 FK로 묶은 필드 명 적어준다.
    private Member_OneToOne_양방향 member;
}