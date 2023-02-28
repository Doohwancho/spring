package com.example.hexagonal.dto.deposit;

import com.example.hexagonal.vo.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositResponse {

    private String owner;
    private Long balance;
    private Long depositAmount;

    public DepositResponse(Account account, Long amount) {
        this.owner = account.getOwnerName();
        this.balance = account.getBalance();
        this.depositAmount = amount;
    }
}
