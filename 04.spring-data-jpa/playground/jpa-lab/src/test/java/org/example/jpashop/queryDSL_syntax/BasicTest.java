package org.example.jpashop.queryDSL_syntax;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.jpashop.domain.QMember.member;
import static org.example.jpashop.domain.QTeam.team;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.QMember;
import org.example.jpashop.domain.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@DisplayName("jqpl vs Querydsl 비교")
class BasicTest {
    
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
    @DisplayName("JPQL 이용한 쿼리 테스트")
    void jpql() {
        String qlString = "select m from Member m " +
            "where m.userName = :userName";
        String userName = "member1";
        
        Member findMember = em.createQuery(qlString, Member.class)
            .setParameter("userName", userName)
            .getSingleResult();
        
        assertThat(findMember.getUserName()).isEqualTo(userName);
    }
    
    @Test
    @DisplayName("Querydsl를 이용한 쿼리 테스트")
    void querydsl() {
        String username = "member1";
        
        Member findMember = queryFactory
            .selectFrom(member)
            .where(member.userName.eq(username))
            .fetchOne();
        
        assertThat(findMember.getUserName()).isEqualTo(username);
    }
}
