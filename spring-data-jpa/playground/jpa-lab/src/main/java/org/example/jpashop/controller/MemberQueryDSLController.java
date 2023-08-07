package org.example.jpashop.controller;

import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.jpashop.dto.MemberTeamDto;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.example.jpashop.repository.queryDSL.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberQueryDSLController {
    
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberJpaRepository.search(condition);
    }
    
    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageSimple(condition, pageable);
    }
    
    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageComplex(condition, pageable);
    }
    
    
    @Data
    public static class MemberSearchCondition {
        
        private String userName;
        private String teamName;
        private Integer ageGoe; //Goe = greater or equal to
        private Integer ageLoe; //Loe = lower or equal to
    }
    
}
