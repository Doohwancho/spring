package com.kata.orderinhexagonal.member.adapter.in.web;

import com.kata.orderinhexagonal.auth.JwtProvider;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberResponse;
import com.kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final CreateMemberUsecase memberUsecase;
    private final JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<CreateMemberResponse> createMember(@RequestBody @Valid CreateMemberRequest request, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }

        Member member = memberUsecase.join(request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + jwtProvider.createJwtToken(member.getId()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(new CreateMemberResponse(member));
    }
}
