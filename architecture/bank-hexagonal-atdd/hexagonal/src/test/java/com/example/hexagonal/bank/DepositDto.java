package com.example.hexagonal.bank;

import lombok.Getter;

@Getter
class DepositDto {
    Long id;
    Long amount;

    public DepositDto(Long id, long amount) {
        this.id = id;
        this.amount = amount;
    }
}
