package org.example.jpashop.repository.queryDSL;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jpashop.controller.MemberQueryDSLController.MemberSearchCondition;
import org.example.jpashop.dto.MemberTeamDto;
import org.example.jpashop.dto.QMemberTeamDto;
import org.example.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static org.example.jpashop.domain.QMember.member;
import static org.example.jpashop.domain.QTeam.team;


@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory; //QueryDSL

    public void save(Member member) {
        em.persist(member);
    }
    
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    
    public Optional<Member> findById(Long id) {
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }
    
    //jpql ver
//    public List<Member> findByUsername(String userName) {
//        return em.createQuery("select m from Member m where m.userName = :userName", Member.class) //:userName은 파라미터로 받은 userName을 가르키는 듯?
//            .setParameter("userName", userName)
//            .getResultList();
//    }
    
    //jpql을 queryDSL을 쓰니까, 이렇게 좋아지네?
    public List<Member> findByUserName(String userName) {
        return queryFactory
            .selectFrom(member)
            .where(member.userName.eq(userName))
            .fetch();
    }
    
    
    
    //jpql ver
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m", Member.class)
//            .getResultList();
//    }

    //queryDSL version
    public List<Member> findAll() {
        return queryFactory
                .selectFrom(member) //훨씬 간결하네?
                .fetch();
    }

    //TODO - builder from queryDSL (ver1)
    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();

        if (hasText(condition.getUserName())) {
            builder.and(member.userName.eq(condition.getUserName()));
        }

        if (hasText(condition.getTeamName())) {
            builder.and(team.name.eq(condition.getTeamName()));
        }

        if (condition.getAgeGoe() != null) { //primitive type은 != null로 값이 있나 체크한다.
            builder.and(member.age.goe(condition.getAgeGoe()));
        }

        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.userName,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    //TODO - searchByBuilder()와 같은데, 빌더를 메서드로 쪼갠 것
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
