package com.example.hexagonal.vo;


import com.example.hexagonal.exception.CannotWithdrawBalanceIsBelowZero;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ownerName;
    Long balance;

    public Long setBalance(Long amount){
        if(balance + amount < 0){
            throw new CannotWithdrawBalanceIsBelowZero("본인이 가진 액수 이상으로 출금할 수 없습니다.");
        }
        balance += amount;
        return balance;
    }
}
