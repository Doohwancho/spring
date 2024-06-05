package org.example.jpashop.queryDSL_syntax.intermediate.동적쿼리;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.jpashop.domain.QMember.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class 동적쿼리Test {
    
    @Autowired
    EntityManager em;
    
    JPAQueryFactory queryFactory;
    
    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
        
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
    

    @Test
    @DisplayName("동적 쿼리 Where 테스트")
    void dynamic_query_where() {
        String username = "member1";
        Integer age = null;
        
        List<Member> result = searchMember(username, age);
    }
    
    private List<Member> searchMember(String username, Integer age) {
        return queryFactory
            .selectFrom(member)
            .where(usernameEq(username), ageEq(age))
            .fetch();
    }
    
    private BooleanExpression usernameEq(String username) {
        if (username == null) {
            return null;
        }
        return member.userName.eq(username);
    }
    
    private BooleanExpression ageEq(Integer age) {
        if (age == null) {
            return null;
        }
        return member.age.eq(age);
    }
}
