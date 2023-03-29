package com.kata.orderinhexagonal.member.application.service;

import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import com.kata.orderinhexagonal.member.application.port.out.FindMemberPort;
import com.kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import com.kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements CreateMemberUsecase {
    private final MemberJoinValidator joinValidator;
    private final PasswordEncoder passwordEncoder;
    private final SaveMemberPort saveMemberPort;
    private final FindMemberPort findMemberPort;

    public Member join(CreateMemberRequest request) {
        joinValidator.verifyExistsEmail(request.getEmail());

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .location(request.getLocation())
                .build();

        saveMemberPort.save(member);

        return member;
    }

    public Member getMemberById(Long id) {
        return findMemberPort.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found with id:" + id));
    }
}
