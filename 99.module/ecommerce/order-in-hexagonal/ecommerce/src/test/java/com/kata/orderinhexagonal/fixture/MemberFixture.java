package com.kata.orderinhexagonal.fixture;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberRepository;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.util.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

@Component
public class MemberFixture {
    @Autowired
    CreateMemberUsecase createMemberUsecase;

    @Autowired
    MemberRepository repository;

    public void clearMember() {
        repository.deleteAll();
    }
    public Member createMember(String name, String email, String location) {
        CreateMemberRequest request = new CreateMemberRequest(name, "doohwancho1234!", email, location);
        Member member = createMemberUsecase.join(request);
        return member;
    }

    public MockHttpServletResponse getAccessToken(String name, String email, String location, MockMvc mockMvc, ObjectMapper objectMapper) throws
            Exception {
        String password = "doohwancho1234!";
        CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);
        return mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();
    }

    public MemberEntity getMember(long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
