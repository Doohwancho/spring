package com.example.hexagonal.dto.withdraw;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawRequest {

    private Long id;
    private Long withdrawAmount;
    private String owner;

    public WithdrawRequest(Long id, String owner, Long withdrawAmount) {
        this.id = id;
        this.owner = owner;
        this.withdrawAmount = withdrawAmount;
    }

    public static WithdrawRequest of(Long id, String owner, Long withdrawAmount) {
        return new WithdrawRequest(id, owner, withdrawAmount);
    }
}
