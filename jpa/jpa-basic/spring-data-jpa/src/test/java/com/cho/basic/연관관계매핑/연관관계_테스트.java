package com.cho.basic.연관관계매핑;


import com.cho.basic.연관관계매핑.OneToOne.MemberRepository;
import com.cho.basic.연관관계매핑.OneToOne.MyPageRepository;

import com.cho.basic.연관관계매핑.ManyToOne.PhoneRepository;
import com.cho.basic.연관관계매핑.ManyToOne.UserRepository;
import com.cho.basic.연관관계매핑.ManyToOne.Phone;
import com.cho.basic.연관관계매핑.ManyToOne.User;
import com.cho.basic.연관관계매핑.OneToOne.Member;
import com.cho.basic.연관관계매핑.OneToOne.MyPage;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class 연관관계_테스트 {

    private final Logger LOGGER = Logger.getLogger(연관관계_테스트.class.getName());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MyPageRepository myPageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;



    /**
     * Member(주인):MyPage = 1:1 양방향 테스트
     *
     * 1:1 단방향 테스트 하려고 하인 쪽 @OneToOne(mappedBy = "myPage_oneToOne_양방향") 을 주석처리 했지만 에러난다.
     * 1:1 단방향은 없는 듯 하다(예상)
     * 1:1 양방향 에서, 하인쪽 insert시, 주인 객체의 update가 일어나는걸 확인할 수 있다.
     */
    @Test
    public void 양방향_ONE_TO_ONE_테스트() throws Exception {
        Member member = Member.builder()
                .name("test")
                .build();

        MyPage myPage = MyPage.builder()
                .name("마이페이지")
                .build();

        // 양방향 연결
        member.setMyPage(myPage);

        memberRepository.save(member);
        myPageRepository.save(myPage);

        // when
        List<Member> list = memberRepository.findAll();
        MyPage firstMyPage = list.get(0).getMyPage();

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

    /**
     * Phone(주인):User = N:1 단방향 테스트
     *
     * 1:1 단방향 테스트 하려고 하인 쪽 @OneToOne(mappedBy = "myPage_oneToOne_양방향") 을 주석처리 했지만 에러난다.
     * 1:1 단방향은 없는 듯 하다(예상)
     * 1:1 양방향 에서, 하인쪽 insert시, 주인 객체의 update가 일어나는걸 확인할 수 있다.
     */
    @Test
    public void 단방향_ManyToOne_테스트(){
        User user1 = new User("name1");
        User user2 = new User("name2");
        User user3 = new User("name3");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Phone phone = new Phone("010-1234-1234");

        phone.setUser(user1);
        phoneRepository.save(phone);

        List<Phone> listOfPhones = phoneRepository.findAll();

        for(Phone p : listOfPhones){
            LOGGER.info(p.toString()+" "+ p.getNumber().toString());
        }

        userRepository.deleteAll();
        phoneRepository.deleteAll();

        /**
         * console.log
         *
         * step1) @GeneratedValue(strategy=GenerationType.AUTO) 으로 했는데, 내부적으로는 SEQUENCE로 결정되었나봄.
         *
         * Hibernate:
         *     call next value for hibernate_sequence
         * Hibernate:
         *     call next value for hibernate_sequence
         * Hibernate:
         *     call next value for hibernate_sequence
         * Hibernate:
         *     call next value for hibernate_sequence
         *
         *
         * //step2) user1,2,3 insert
         * Hibernate:
         *     insert
         *     into
         *         user
         *         (name, seq)
         *     values
         *         (?, ?)
         * Hibernate:
         *     insert
         *     into
         *         user
         *         (name, seq)
         *     values
         *         (?, ?)
         * Hibernate:
         *     insert
         *     into
         *         user
         *         (name, seq)
         *     values
         *         (?, ?)
         *
         * //step3) phoneRepository.save(phone); -> 주인을 insert했기 때문에, 괜찮. 만약 User가 주인이었다면, Phone insert시 주인인 User도 update 됬겠지.
         * Hibernate:
         *     insert
         *     into
         *         phone
         *         (number, user_id, seq)
         *     values
         *         (?, ?, ?)
         *
         * //step4) List<Phone> listOfPhones = phoneRepository.findAll();
         * Hibernate:
         *     select
         *         phone0_.seq as seq1_2_,
         *         phone0_.number as number2_2_,
         *         phone0_.user_id as user_id3_2_
         *     from
         *         phone phone0_
         * com.cho.basic.연관관계매핑.ManyToOne.Phone@755242c5 010-1234-1234
         *
         * //step5) delete all
         * Hibernate:
         *     select
         *         user0_.seq as seq1_3_,
         *         user0_.name as name2_3_
         *     from
         *         user user0_
         * Hibernate:
         *     select
         *         phone0_.seq as seq1_2_,
         *         phone0_.number as number2_2_,
         *         phone0_.user_id as user_id3_2_
         *     from
         *         phone phone0_
         */
    }

}
