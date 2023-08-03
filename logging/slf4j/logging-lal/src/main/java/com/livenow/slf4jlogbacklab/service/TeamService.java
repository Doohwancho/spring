package com.livenow.slf4jlogbacklab.service;


import com.livenow.slf4jlogbacklab.domain.Member;
import com.livenow.slf4jlogbacklab.domain.Team;
import com.livenow.slf4jlogbacklab.exception.NotFoundException;
import com.livenow.slf4jlogbacklab.repository.MemberRepository;
import com.livenow.slf4jlogbacklab.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeamService {
    
    private Logger logger = LoggerFactory.getLogger(TeamService.class);
    
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    
    public TeamService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }
    
    @Transactional
    public void save(String name) {
        Team team = new Team(name);
        teamRepository.save(team);
    }
    
    @Transactional
    public void addMember(String teamName, String memberName) {
        Team team = findTeam(teamName);
        Member member = findMember(memberName);
        team.addMember(member);
    }
    
    private Member findMember(String memberName) {
        Optional<Member> member = memberRepository.findByName(memberName);
        if (member.isEmpty()) {
            String detailMessage = String.format("NotFoundException: Cannot find Member by input value, Input: %s", memberName); //TODO - 에러 메시지 최대한 자세하게 하기 위해 input도 넣어줬다.
            logger.info(detailMessage); //TODO - 상태 처리 시, 의도되로 동작 안했다면, .info() 레벨로 처리 후, 에러 던지고, 에러는 .error()로 별도처리.
            throw new NotFoundException(detailMessage);
        }
        return member.get();
    }
    
    private Team findTeam(String teamName) {
        Optional<Team> team = teamRepository.findByName(teamName);
        if (team.isEmpty()) {
            String detailMessage = String.format("NotFoundException: Cannot find Team by input value. Input: %s", teamName);
            logger.info(detailMessage);
            throw new NotFoundException(detailMessage);
        }
        return team.get();
    }
}
