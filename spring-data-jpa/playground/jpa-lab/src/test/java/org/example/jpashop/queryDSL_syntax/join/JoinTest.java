package org.example.jpashop.queryDSL_syntax.join;

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
class JoinTest {
    
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
    @DisplayName("join 테스트")
    void join() {
        List<Member> result = queryFactory
            .selectFrom(member)
            .join(member.team, team)
            .where(team.name.eq("Team A"))
            .fetch();
        
        assertThat(result)
            .extracting("userName")
            .containsExactly("member1", "member2");
    }
    
    @Test
    @DisplayName("join on 테스트")
    void join_on() {
        List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(member.team, team)
            .on(team.name.eq("Team A"))
            .fetch();
        
        for (Tuple tuple : result) {
            System.out.println(tuple);
        }
        
        assertThat(result.size()).isEqualTo(4);
    }
    
    @Test
    @DisplayName("join on 연관관계 없을 때, 테스트")
    void no_relation_join_on() {
        em.persist(new Member("Team A"));
        em.persist(new Member("Team B"));
        em.persist(new Member("Team C"));
        
        List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(team).on(member.userName.eq(team.name))
            .fetch();
        
        for (Tuple tuple : result) {
            System.out.println(tuple);
        }
    }
    
    @PersistenceUnit
    EntityManagerFactory emf;
    
    @Test
    @DisplayName("fetch join 테스트")
    void fetch_join() {
        Member findMember = queryFactory
            .selectFrom(member)
            .join(member.team, team).fetchJoin()
            .where(member.userName.eq("member1"))
            .fetchOne();
        
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).isTrue();
    }
    
    @Test
    @DisplayName("subquery 테스트")
    void sub_query() {
        QMember memberSub = new QMember("memberSub");
        
        Member findMember = queryFactory
            .selectFrom(QMember.member)
            .where(QMember.member.age.eq(
                JPAExpressions
                    .select(memberSub.age.max())
                    .from(memberSub)
            )).fetchOne();
        
        assertThat(findMember.getAge()).isEqualTo(40);
    }
}
