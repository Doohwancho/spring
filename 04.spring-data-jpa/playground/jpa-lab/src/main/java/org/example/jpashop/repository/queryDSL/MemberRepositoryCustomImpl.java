package org.example.jpashop.repository.queryDSL;

import static org.springframework.util.StringUtils.hasText;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.example.jpashop.controller.MemberQueryDSLController.MemberSearchCondition;
import org.example.jpashop.domain.Member;
import org.example.jpashop.dto.MemberTeamDto;
import org.example.jpashop.dto.QMemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import static org.example.jpashop.domain.QMember.member;
import static org.example.jpashop.domain.QTeam.team;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;
    
    public MemberRepositoryCustomImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        return queryFactory
            .select(new QMemberTeamDto(
                member.id.as("memberId"),
                member.userName,
                member.age,
                team.id.as("teamId"),
                team.name.as("teamName")))
            .from(member)
            .leftJoin(member.team, team)
            .where(
                usernameEq(condition.getUserName()),
                teamNameEq(condition.getTeamName()),
                ageGoe(condition.getAgeGoe()),
                ageLoe(condition.getAgeLoe())
            )
            .fetch();
    }
    
    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition,
        Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
            .select(new QMemberTeamDto(
                member.id.as("memberId"),
                member.userName,
                member.age,
                team.id.as("teamId"),
                team.name.as("teamName")))
            .from(member)
            .leftJoin(member.team, team)
            .where(
                usernameEq(condition.getUserName()),
                teamNameEq(condition.getTeamName()),
                ageGoe(condition.getAgeGoe()),
                ageLoe(condition.getAgeLoe())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults(); //.fetchResults()을 사용
        //TODO - what is .fetchResults()?
        //fetches both the content (based on the given pagination parameters) and the total count in a single call.
        
        //pros
        //This is more efficient because it only requires one round trip to the database.
        
        //cons
        //even if the pagination doesn't require the total count (e.g., when you're fetching the last page and already know the total count),
        //it will still retrieve it, which can be an unnecessary overhead
        
        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();
        
        return new PageImpl<>(content, pageable, total);
    }
    
    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition,
        Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
            .select(new QMemberTeamDto(
                member.id.as("memberId"),
                member.userName,
                member.age,
                team.id.as("teamId"),
                team.name.as("teamName")))
            .from(member)
            .leftJoin(member.team, team) //member.team으로 조인하네?
            .where(
                usernameEq(condition.getUserName()),
                teamNameEq(condition.getTeamName()),
                ageGoe(condition.getAgeGoe()),
                ageLoe(condition.getAgeLoe())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        
        JPAQuery<Member> countQuery = queryFactory //Separates the fetching of content and the total count into two distinct queries
            .select(member)
            .from(member)
            .leftJoin(member.team, team)
            .where(
                usernameEq(condition.getUserName()),
                teamNameEq(condition.getTeamName()),
                ageGoe(condition.getAgeGoe()),
                ageLoe(condition.getAgeLoe())
            );
        //TODO - Q. what is PageableExecutionUtils.getPage()?
        //performance optimization version of pagenation
        
        //pros
        // avoids count query If the total count fetch is not necessary
        
        //cons
        //it might require two round trips to the database: one for the content and one for the count.
        //However, as mentioned, the count query might be skipped in some cases.
        
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }
    
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.userName.eq(username) : null;
    }
    
    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }
    
    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }
    
    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
