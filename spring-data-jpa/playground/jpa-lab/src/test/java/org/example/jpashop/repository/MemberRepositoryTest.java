package org.example.jpashop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.example.jpashop.controller.MemberQueryDSLController.MemberSearchCondition;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.QMember;
import org.example.jpashop.domain.Team;
import org.example.jpashop.dto.MemberTeamDto;
import org.example.jpashop.repository.queryDSL.MemberRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Disabled
@SpringBootTest
@Transactional
class MemberRepositoryTest {
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    MemberRepository memberRepository;
    
    @Test
    void save_member() {
        Member member = new Member("jayden", 30);
        memberRepository.save(member);
        
        Member findMember = memberRepository.findById(member.getId()).get();
        
        assertThat(findMember).isEqualTo(member);
    }
    
    @DisplayName("queryDSL 조건검색")
    @Test
    void search_member_and_team() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        em.persist(teamA);
        em.persist(teamB);
        
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("Team B");
    
        List<MemberTeamDto> result = memberRepository.search(condition);
        
        assertThat(result).extracting("username").containsExactly("member4");
    }
    
    @DisplayName("Pagenation 범위검색")
    @Test
    void search_page_simple() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        em.persist(teamA);
        em.persist(teamB);
        
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        
        MemberSearchCondition condition = new MemberSearchCondition();
        PageRequest pageRequest = PageRequest.of(0, 3);
        
        Page<MemberTeamDto> result = memberRepository.searchPageSimple(condition, pageRequest);
        
        assertThat(result.getSize()).isEqualTo(3);
        assertThat(result.getContent()).extracting("username")
            .containsExactly("member1", "member2", "member3");
    }
    
    @Test
    void querydsl_predicate_executor() {
        int age = 30;
        Iterable<Member> result = memberRepository.findAll(QMember.member.age.eq(age));
        
        for (Member member : result) {
            System.out.println(member);
        }
    }
    
}
    
