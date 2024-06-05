package org.example.jpashop.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.example.jpashop.domain.Member;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private MemberJpaRepository memberRepository;
    
    @Autowired
    EntityManager em;
    
    @Test
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setUserName("kim");
        
        //when
        Long savedId = memberService.join(member);
        
        //then
        em.flush();
        //TODO - Q. MemberServiceTest.java에서, 그냥 Long savedId = memberService.join(member); 만 하면 되는데, em.flush(); 도 따로 해주네, 왜지?
        
        //A. write-behind에 sql문 담기고 아직 db에 안쏜 상태 예방하려고구나.
    
        //flush() synchronize the persistence context to the underlying database.
        // It forces any pending changes to be written to the database immediately and also flushes the EntityManager's internal cache.
        
        //그래야 그 다음 코드인 assertEquals(member, memberRepository.findOne(savedId)); 을 했을 때,
        // db에서 실제 값이 있어야 땡겨올 수 있지.
        
        assertEquals(member, memberRepository.findOne(savedId));
    }
    
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setUserName("kim");
        
        Member member2 = new Member();
        member2.setUserName("kim");
        
        //when
        //then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member1);
            memberService.join(member2);
        });
    }
}