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
        em.flush(); //flush() synchronize the persistence context to the underlying database. It forces any pending changes to be written to the database immediately and also flushes the EntityManager's internal cache.
        
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