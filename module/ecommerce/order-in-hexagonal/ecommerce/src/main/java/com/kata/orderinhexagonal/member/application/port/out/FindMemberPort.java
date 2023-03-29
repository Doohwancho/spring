package com.kata.orderinhexagonal.member.application.port.out;

import com.kata.orderinhexagonal.member.domain.Member;

import java.util.Optional;

public interface FindMemberPort {
    Optional<Member> findById(Long id);
}
