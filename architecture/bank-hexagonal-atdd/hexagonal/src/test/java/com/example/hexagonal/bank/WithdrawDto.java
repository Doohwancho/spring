package com.example.hexagonal.bank;

import lombok.Getter;

@Getter
class WithdrawDto {
    Long id;
    Long amount;

    public WithdrawDto(Long id, long amount) {
        this.id = id;
        this.amount = amount;
    }
}
