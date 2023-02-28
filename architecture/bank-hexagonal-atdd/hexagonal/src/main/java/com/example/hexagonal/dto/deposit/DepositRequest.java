package com.example.hexagonal.dto.deposit;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class DepositRequest {

    private Long id;
    private Long depositAmount;
    private String owner;

    public DepositRequest(Long id, String owner, Long depositAmount) {
        this.id = id;
        this.owner = owner;
        this.depositAmount = depositAmount;
    }

    public static DepositRequest of(Long id, String owner, Long depositAmount) {
        return new DepositRequest(id, owner, depositAmount);
    }
}