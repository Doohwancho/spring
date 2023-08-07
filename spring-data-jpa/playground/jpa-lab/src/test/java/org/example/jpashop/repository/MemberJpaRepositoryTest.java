package org.example.jpashop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import org.example.jpashop.controller.MemberQueryDSLController.MemberSearchCondition;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Team;
import org.example.jpashop.dto.MemberTeamDto;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    
    @Autowired
    EntityManager em;
    
    @Autowired
    MemberJpaRepository memberJpaRepository;
    
    
    
    @DisplayName("jpql로 쓰여진 .findById() 테스트")
    @Test
    void save_member() {
        Member member = new Member("jayden", 30);
        memberJpaRepository.save(member);
        
        Member findMember = memberJpaRepository.findById(member.getId()).get();
        
        assertThat(findMember).isEqualTo(member);
    }
    
    
    @DisplayName("builder from queryQSL 이용")
    @Test
    void search_builder_member_and_team() {
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
        
        MemberSearchCondition condition = new MemberSearchCondition(); //1. sql의 where절 조건을 담은 클래스 만들어서
        condition.setAgeGoe(35); //2. 조건 넣고, (Goe = greater or equal to)
        condition.setAgeLoe(40); //(Loe = less than or equal to)
        condition.setTeamName("Team B");
        
        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition); //builder from querydsl 에 넣는다.
        
        assertThat(result).extracting("username").containsExactly("member4");
    }
    
    @DisplayName("builder from queryQSL랑 같은데 builder를 메서드로 뺀 것")
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
        
        List<MemberTeamDto> result = memberJpaRepository.search(condition);
        
        assertThat(result).extracting("username").containsExactly("member4");
    }
}