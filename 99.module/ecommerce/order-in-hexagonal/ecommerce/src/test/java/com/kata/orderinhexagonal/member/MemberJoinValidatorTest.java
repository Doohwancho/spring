package com.kata.orderinhexagonal.member;

import com.kata.orderinhexagonal.member.adapter.out.persistence.ExistsEmailException;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberRepository;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import com.kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MemberJoinValidatorTest extends TestConfig {

    @Autowired
    MemberJoinValidator memberJoinValidator;
    @Autowired
    SaveMemberPort saveMemberPort;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    Validator validator;


    @Test
    @DisplayName("존재하지 않는 이메일")
    void 이메일_존재여부_확인() {
        //given
        String email = "hahahahhahahaha@gmail.com";
        //when
        boolean verify = memberJoinValidator.verifyExistsEmail(email);
        //then
        Assertions.assertThat(verify).isFalse();
    }

    @Test
    @DisplayName("존재하는 이메일")
    void 이메일_존재여부_확인_이미존재() {
        //given
        String email = "doohwancho@gmail.com";
        Member member = Member.builder()
                .email(email)
                .password("doohwancho1234!")
                .name("조두환")
                .location("김포시")
                .build();

        saveMemberPort.save(member);

        //when & then
        Assertions.assertThatThrownBy(() -> memberJoinValidator.verifyExistsEmail(email))
                .isInstanceOf(ExistsEmailException.class);
    }

    //TODO - c-b-1-3: email validator
    @ParameterizedTest
    @CsvSource({
            "test@example.com, true",
            "test@example.co, true",
            "test@subdomain.example.com, true",
            "test@example, false",
            "test@.com, false",
            "test@, false",
            "test, false",
            "test@subdomain., false",
            "test@subdomain..com, false",
            "test@subdomain.example..com, false"
    })
    @DisplayName("이메일 형식 유효여부 확인")
    void 이메일_형식_유효여부_확인(String email, boolean expected) {
        //given
        CreateMemberRequest request = new CreateMemberRequest(email, "password!123", "name", "location");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        if (expected == true) {
            assertFalse(errors.hasErrors(), "Expected email to be valid, therefore no validation errors were found");
        } else if(expected == false){
            assertTrue(errors.hasErrors(), "Expected email to be invalid, therefore validation errors were found");
        }
    }

    //TODO - c-b-1-3: password validator
    @ParameterizedTest
    @CsvSource({
            "123456a!, true",
            "12345678901234a!, true",
            "1, false",
            "12345678, false",
            "a, false",
            "aaaaaaaa1, false",
            "!, false",
            "!@#$%^&*1, false",
            "!@#$%^&*a, false",
            "aaaaaaaaaaaaaaaaa, false"
    })
    @DisplayName("비밀번호 형식 유효여부 확인")
    void 비밀번호_형식_유효여부_확인(String password, boolean expected) {
        //given
        CreateMemberRequest request = new CreateMemberRequest("doohwancho@gmail.com", password, "name", "location");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        //when
        validator.validate(request, errors);

        //then
        if (expected == true) {
            assertFalse(errors.hasErrors(), "Expected password to be valid, therefore no validation errors were found");
        } else if(expected == false){
            assertTrue(errors.hasErrors(), "Expected password to be invalid, therefore validation errors were found");
        }
    }
}
