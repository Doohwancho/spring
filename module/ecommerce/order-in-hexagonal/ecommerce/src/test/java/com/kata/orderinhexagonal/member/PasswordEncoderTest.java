package com.kata.orderinhexagonal.member;

import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordEncoderTest {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Test 
    void 비밀번호_인코딩() {
        //given
        String password = "testPassword";

        //when
        String encodedPassword = passwordEncoder.encode(password);

        //then
        Assertions.assertThat(password).isNotEqualTo(encodedPassword);
    }
}
