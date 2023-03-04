package com.practice.service;

import com.practice.domain.Member;
import com.practice.dto.JoinDto;
import com.practice.dto.JwtTokenDto;
import com.practice.dto.LoginDto;
import com.practice.repository.member.MemberRepository;
import com.practice.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("패스워드 암호화 테스트")
    void shouldEncodePassword() {
        // given
        String rawPassword = "12345678";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // then
        assertAll(
                () -> assertNotEquals(rawPassword, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }

    @Test
    @DisplayName("일반 유저 회원가입 테스트")
    @Transactional
    public void shouldBeAbleToRegisterUserWithROLE_USER() {
        // given
        String userName = UUID.randomUUID().toString();
        String userPassword = "password";
        String userIntro = "user_intro";

        JoinDto joinDto = new JoinDto();
        joinDto.setUsername(userName);
        joinDto.setPassword(userPassword);
        joinDto.setIntro(userIntro);

        // when
        Long memberId = memberService.register(joinDto);
        Member result = memberRepository.findById(memberId).get();

        // then
        assertThat(result.getUsername()).isEqualTo(joinDto.getUsername());
        assertThat(passwordEncoder.matches(userPassword, result.getPassword())).isEqualTo(true);
        assertThat(result.getIntro()).isEqualTo(joinDto.getIntro());
        assertThat(result.getRoles().get(0)).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("ADMIN 유저 회원가입 테스트")
    @Transactional //for Member.authority is LAZY
    public void shouldBeAbleToRegisterADMINWithROLE_ADMIN() {

        // given
        String userName = UUID.randomUUID().toString();
        String userPassword = "password";
        String userIntro = "user_intro";

        JoinDto joinDto = new JoinDto();
        joinDto.setUsername(userName);
        joinDto.setPassword(userPassword);
        joinDto.setIntro(userIntro);

        // when
        Long memberId = memberService.registerAdmin(joinDto);
        Member result = memberRepository.findById(memberId).get();

        // then
        assertThat(result.getUsername()).isEqualTo(joinDto.getUsername());
        assertThat(passwordEncoder.matches(userPassword, result.getPassword())).isEqualTo(true);
        assertThat(result.getIntro()).isEqualTo(joinDto.getIntro());
        assertThat(result.getRoles().get(0)).isEqualTo("ROLE_ADMIN");
    }

}
