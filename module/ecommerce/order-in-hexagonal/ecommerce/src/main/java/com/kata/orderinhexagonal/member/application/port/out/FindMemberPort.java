package com.kata.orderinhexagonal.member.application.port.out;

import com.kata.orderinhexagonal.member.domain.Member;

public interface FindMemberPort {
    Member findById(Long id);
}
