package com.kata.orderinhexagonal.member.application.port.in;


import com.kata.orderinhexagonal.member.domain.Member;

public interface CreateMemberUsecase {
    Member join(CreateMemberRequest request);
}
