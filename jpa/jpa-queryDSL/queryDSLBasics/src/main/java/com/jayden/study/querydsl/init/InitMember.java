package com.jayden.study.querydsl.init;

import com.jayden.study.querydsl.entity.Member;
import com.jayden.study.querydsl.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitMember {

    /**
     * Test code가 이것 때문에 막혀서 주석 처리 해놓음
     */

//    private final InitMemberService initMemberService;

//    @PostConstruct
//    public void init() {
//        initMemberService.init();
//    }

//    @Component
//    static class InitMemberService {
//        @PersistenceContext
//        private EntityManager em;
//
//        @Transactional
//        public void init() {
//            Team teamA = new Team("Team A");
//            Team teamB = new Team("Team B");
//            em.persist(teamA);
//            em.persist(teamB);
//
//            for (int i = 0; i < 100; i++) {
//                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
//                em.persist(new Member("member" + i, i, selectedTeam));
//            }
//        }
//    }
}
