package com.kata.orderinhexagonal.member.application.port.out;

import com.kata.orderinhexagonal.member.domain.Member;

public interface SaveMemberPort {
    void save(Member member);
}
