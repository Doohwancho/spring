package org.example.jpashop.queryDSL_syntax.orderby;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.jpashop.domain.QMember.member;
import static org.example.jpashop.domain.QTeam.team;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.QMember;
import org.example.jpashop.domain.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderByTest {
    
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
    @DisplayName("orderby 쿼리 테스트")
    void sort() {
        List<Member> members = queryFactory
            .selectFrom(member)
            .where(member.age.goe(10))
            .orderBy(member.age.desc(), member.userName.asc().nullsLast())
            .fetch();
        
        assertThat(members.size()).isEqualTo(4);
    }
}
