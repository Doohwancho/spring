package com.kata.orderinhexagonal.member.application.port.in;

import com.kata.orderinhexagonal.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberResponse {
    private long id;
    private String name;
    private String email;
    private String location;

    public CreateMemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.location = member.getLocation();
    }
}
