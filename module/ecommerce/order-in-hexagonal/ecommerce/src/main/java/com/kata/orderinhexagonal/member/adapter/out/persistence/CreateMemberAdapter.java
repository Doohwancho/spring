package com.kata.orderinhexagonal.member.adapter.out.persistence;

import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import com.kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@AllArgsConstructor
@Component
public class CreateMemberAdapter implements PasswordEncoder, SaveMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public String encode(String password) {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }

    @Override
    public void save(Member member) {
        MemberEntity entity = memberMapper.toEntity(member);
        memberRepository.save(entity);
        member.assignId(entity.getId());
    }
}
