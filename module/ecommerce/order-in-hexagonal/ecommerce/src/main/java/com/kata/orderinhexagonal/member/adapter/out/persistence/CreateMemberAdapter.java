package com.kata.orderinhexagonal.member.adapter.out.persistence;

import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@AllArgsConstructor
@Component
public class CreateMemberAdapter implements PasswordEncoder {

    MemberRepository memberRepository;
    @Override
    public String encode(String password) {
        return new DigestUtils("SHA3-256").digestAsHex(password);
    }
}
