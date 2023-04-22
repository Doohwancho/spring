package com.kata.orderinhexagonal.member;

import com.kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import com.kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class SaveMemberPortTest extends TestConfig {
    @Autowired
    SaveMemberPort saveMemberPort;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void member_save() {
        // given
        String email = "doohwancho@gmail.com";
        String password = "doohwancho1234!";
        String name = "조두환";
        String location = "김포시";
        Member member = new Member(email, passwordEncoder.encode(password), name, location);
        // when
        saveMemberPort.save(member);
        // then
        Assertions.assertThat(member.getId()).isPositive();
    }

}
