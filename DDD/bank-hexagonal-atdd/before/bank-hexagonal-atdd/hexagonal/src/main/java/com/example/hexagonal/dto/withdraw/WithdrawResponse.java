package com.example.hexagonal.dto.withdraw;

import com.example.hexagonal.vo.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawResponse{

    private String owner;
    private Long balance;
    private Long withdrawAmount;

    public WithdrawResponse(Account account, Long amount) {
        this.owner = account.getOwnerName();
        this.balance = account.getBalance();
        this.withdrawAmount = amount;
    }
}