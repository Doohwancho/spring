package com.kata.orderinhexagonal.member;

import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.member.application.service.MemberService;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.Mockito.*;

public class MemberServiceTest extends TestConfig {

    @Autowired
    MemberService memberService;

    @SpyBean
    PasswordEncoder passwordEncoder;

    @Test 
    public void join_test() {
        //given
        String email = "testEmail";
        String password = "testPassword";
        String name = "testName";
        String location = "testLocation";

        //build member using builder in lombok
        CreateMemberRequest member = new CreateMemberRequest(email, password, name, location);

        //when
        Member savedMember = memberService.join(member);
        
        //then
        Member result = memberService.getMemberById(savedMember.getId());

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isPositive();
        Assertions.assertThat(result.getEmail()).isEqualTo(email);
        Assertions.assertThat(result.getName()).isEqualTo(name);
        Assertions.assertThat(result.getLocation()).isEqualTo(location);

        verify(passwordEncoder, times(1)).encode(password);
    }

}
