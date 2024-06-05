package org.example.jpashop.queryDSL_syntax.intermediate.bulk_update;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.jpashop.domain.QMember.member;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Team;
import org.example.jpashop.dto.MemberDto;
import org.example.jpashop.dto.QMemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class BulkUpdateTest {
    
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
    @DisplayName("벌크 수정 테스트")
    void bulk_update() {
        long count = queryFactory.
            update(member)
            .set(member.userName, "비회원")
            .where(member.age.lt(29))
            .execute();
        
        assertThat(count).isEqualTo(2);
    }
    
    @Test
    @DisplayName("벌크 삭제 테스트")
    void bulk_delete() {
        long count = queryFactory.
            delete(member)
            .where(member.age.lt(30))
            .execute();
        
        assertThat(count).isEqualTo(2);
    }

}
