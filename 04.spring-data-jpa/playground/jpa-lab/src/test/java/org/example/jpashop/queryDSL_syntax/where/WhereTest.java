package org.example.jpashop.queryDSL_syntax.where;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.example.jpashop.domain.QMember.member;
@SpringBootTest
@Transactional
public class WhereTest {
    @Autowired
    EntityManager em;
    
    JPAQueryFactory queryFactory;
    
    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
        
        em.flush();
        em.clear();
        
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
    }
    
    @AfterEach
    void after(){
        em.flush();
        em.clear();
    }
    
    @Test
    @DisplayName("where절 쿼리 테스트")
    void search() {
        String username = "member1";
        int age = 10;
        
        Member findMember = queryFactory
            .selectFrom(member)
            .where(member.userName.eq(username),
                member.age.eq(age))
            .fetchOne();
        
        assertThat(findMember.getUserName()).isEqualTo(username);
        assertThat(findMember.getAge()).isEqualTo(age);
    }
}
