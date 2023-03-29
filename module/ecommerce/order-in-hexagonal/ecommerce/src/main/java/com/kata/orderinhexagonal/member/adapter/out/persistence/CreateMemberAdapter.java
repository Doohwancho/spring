package com.kata.orderinhexagonal.member.adapter.out.persistence;

import com.kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import com.kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@AllArgsConstructor
@Component
public class CreateMemberAdapter implements MemberJoinValidator, PasswordEncoder, SaveMemberPort {


    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public String encode(String password) {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }

    @Override
    public boolean verifyExistsEmail(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
        }
        return false;
    }

    @Override
    public void save(Member member) {
        MemberEntity entity = memberMapper.toEntity(member);
        memberRepository.save(entity);
        member.assignId(entity.getId());
    }
}
