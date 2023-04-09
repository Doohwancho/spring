package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.member.domain.Member;

public interface ItemOrdererNetworkClient {
    Member loadOrderer(Long ordererId);
}
