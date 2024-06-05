package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.member.domain.Member;

public interface LoadOrdererPort {
    Member load(Long ordererId);
}
