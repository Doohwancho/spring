package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.member.adapter.out.persistence.FindMemberAdapter;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberMapper;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberRepository;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOrdererNetworkMonolithicClient implements ItemOrdererNetworkClient {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadOrderer(Long ordererId) {
        MemberEntity memberEntity = memberRepository.findById(ordererId).orElseThrow(() -> new RuntimeException("no member found"));
        return memberMapper.toDomain(memberEntity);
    }
}
