package org.example.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.example.jpashop.domain.Member;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * why readonly = true for @Transactional?
 *
 * 1. transaction 단계에서, read말고 isnert,update,delete는 동시성 문제로 인해 extra step들이 많은데, read만 할 거면, 이 단계 모두 스킵 가능 -> 성능 빨라짐
 * 2. ensure it's read only -> read 외에 다른 헛짓거리 못하게 됨. secure coding.
 */


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicatedMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        List<Member> findMembers = memberRepository.findByUserName(member.getUserName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String userName) {
        Member member = memberRepository.findOne(id);
        member.setUserName(userName);
    }
}
