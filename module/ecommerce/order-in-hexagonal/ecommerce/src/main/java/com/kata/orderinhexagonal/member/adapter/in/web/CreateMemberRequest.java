package com.kata.orderinhexagonal.member.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateMemberRequest {
    private String email;
    private String password;
    private String name;
    private String location;
}
