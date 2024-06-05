package com.kata.orderinhexagonal.member;

import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberRepository;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class CreateMemberUsecaseTest extends TestConfig {

    @Autowired
    CreateMemberUsecase memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    void join() {
        // given
        String email = "doohwancho@gmail.com";
        String password = "doohwancho1234!";
        String name = "조두환";
        String location = "김포시";
        CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);

        // when
        Member createMember = memberService.join(request);

        // then
        Assertions.assertThat(createMember.getId()).isPositive();
        Assertions.assertThat(createMember.getEmail()).isEqualTo(email);
        Assertions.assertThat(createMember.getPassword()).isNotEqualTo(password);
        Assertions.assertThat(createMember.getName()).isEqualTo(name);
        Assertions.assertThat(createMember.getLocation()).isEqualTo(location);
    }

}
