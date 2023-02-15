package com.cho.basic.연관관계매핑;


import com.cho.basic.repository.연관관계매핑.OneToOneMemberRepository;
import com.cho.basic.repository.연관관계매핑.OneToOneMyPageRepository;

import com.cho.basic.vo.연관관계매핑.OneToOne.Member_OneToOne_양방향;
import com.cho.basic.vo.연관관계매핑.OneToOne.MyPage_OneToOne_양방향;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.util.List;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class 연관관계_테스트 {

    @Autowired
    private OneToOneMemberRepository memberRepository;

    @Autowired
    private OneToOneMyPageRepository myPageRepository;


    /**
     * Member(주인):MyPage = 1:1 양방향 테스트
     *
     * 1:1 단방향 테스트 하려고 하인 쪽 @OneToOne(mappedBy = "myPage_oneToOne_양방향") 을 주석처리 했지만 에러난다.
     * 1:1 단방향은 없는 듯 하다(예상)
     * 1:1 양방향 에서, 하인쪽 insert시, 주인 객체의 update가 일어나는걸 확인할 수 있다.
     */
    @Test
    public void 양방향_ONE_TO_ONE_테스트() throws Exception {
        Member_OneToOne_양방향 member = Member_OneToOne_양방향.builder()
                .name("test")
                .build();

        MyPage_OneToOne_양방향 myPage = MyPage_OneToOne_양방향.builder()
                .name("마이페이지")
                .build();

        // 양방향 연결
        member.setMyPage_oneToOne_양방향(myPage);

        memberRepository.save(member);
        myPageRepository.save(myPage);

        // when
        List<Member_OneToOne_양방향> list = memberRepository.findAll();
        MyPage_OneToOne_양방향 firstMyPage = list.get(0).getMyPage_oneToOne_양방향();

        // then
        assertThat(firstMyPage.getName()).isEqualTo("마이페이지");

        /**
         * console.log
         *
         * 양방향 - Member(주인):MyPage = 1:1
         *
         * Hibernate:
         *     insert
         *     into
         *         member_one_to_one_단방향
         *         (id, login_type, my_page_one_to_one_단방향_id, name)
         *     values
         *         (null, ?, ?, ?)
         *
         *
         * Hibernate:
         *     insert
         *     into
         *         my_page_one_to_one_단방향
         *         (id, name)
         *     values
         *         (null, ?)
         * Hibernate:    ????????? - 주의! 하인에 insert 하면, 주인에 update도 발생한다!
         *     update
         *         member_one_to_one_단방향
         *     set
         *         login_type=?,
         *         my_page_one_to_one_단방향_id=?,
         *         name=?
         *     where
         *         id=?
         *
         * Hibernate:
         *     select
         *         member_one0_.id as id1_3_,
         *         member_one0_.login_type as login_ty2_3_,
         *         member_one0_.my_page_one_to_one_단방향_id as my_page_4_3_,
         *         member_one0_.name as name3_3_
         *     from
         *         member_one_to_one_단방향 member_one0_
         */
    }

}
