package com.kata.orderinhexagonal.member.adapter.out.persistence;

import com.kata.orderinhexagonal.member.application.port.out.FindMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindMemberAdapter implements FindMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member findById(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("member with id of"+id+" were not found"));
        return memberMapper.toDomain(memberEntity);
    }
}
