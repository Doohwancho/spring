package com.kata.orderinhexagonal.member.domain;

import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private long id;
    private String email;
    private String name;
    private String password;
    private String location;

    public Member(String email, String password, String name, String location) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.location = location;
    }

    public static Member toDomain(MemberEntity memberEntity) {
        return new Member(memberEntity.getId(), memberEntity.getEmail(), memberEntity.getName(), memberEntity.getPassword(), memberEntity.getLocation());
    }

    public void assignId(long id) {
        this.id = id;
    }
}
