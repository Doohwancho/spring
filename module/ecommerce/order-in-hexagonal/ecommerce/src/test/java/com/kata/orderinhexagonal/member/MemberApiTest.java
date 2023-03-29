package com.kata.orderinhexagonal.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void 회원가입() throws Exception {
        //given
        String email = "doohwancho@gmail.com";
        String password = "doohwancho1234!";
        String name = "조두환";
        String location = "김포시";
        CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CreateMemberResponse createMemberResponse = objectMapper.readValue(response.getContentAsString(), CreateMemberResponse.class);
        Assertions.assertThat(createMemberResponse.getId()).isPositive();
        Assertions.assertThat(createMemberResponse.getName()).isEqualTo(name);
        Assertions.assertThat(createMemberResponse.getEmail()).isEqualTo(email);
        Assertions.assertThat(createMemberResponse.getLocation()).isEqualTo(location);
    }

}
