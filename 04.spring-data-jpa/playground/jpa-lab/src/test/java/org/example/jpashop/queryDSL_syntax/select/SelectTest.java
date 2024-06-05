package org.example.jpashop.queryDSL_syntax.select;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.jpashop.domain.QMember.member;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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

@SpringBootTest
@Transactional
class SelectTest {
    
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
    @DisplayName("Case 문 테스트")
    void case_clause() {
        List<String> result = queryFactory
            .select(member.age
                .when(10).then("열살")
                .when(20).then("스무살")
                .otherwise("기타"))
            .from(member)
            .fetch();
        
        assertThat(result).contains("열살", "스무살", "기타");
    }
    
    @Test
    @DisplayName("상수 테스트")
    void constant() {
        List<Tuple> result = queryFactory
            .select(member.userName, Expressions.constant("Dev"))
            .from(member)
            .fetch();
        
        for (Tuple tuple : result) {
            System.out.println(tuple); //[member1, "Dev"]
        }
    }
    
    @Test
    @DisplayName("문자 합치기 테스트")
    void string_concat() {
        String result = queryFactory
            .select(member.userName.concat("_").concat(member.age.stringValue()))
            .from(member)
            .where(member.userName.eq("member1"))
            .fetchOne();
        
        assertThat(result).isEqualTo("member1_10");
    }
}
