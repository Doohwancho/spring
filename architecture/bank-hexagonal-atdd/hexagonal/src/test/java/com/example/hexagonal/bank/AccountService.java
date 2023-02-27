package com.example.hexagonal.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class AccountService {

    @Autowired
    AccountRepository repository;

    @Transactional
    public void deposit(DepositDto depositDto) throws AccountDoesNotExist {
        Optional<Account> account = repository.findById(depositDto.getId());
        if (account.isEmpty()) {
            throw new AccountDoesNotExist("계좌가 존재하지 않습니다.");
        }
        Long targetId = account.get().getId();
        Long targetBalance = account.get().setBalance(depositDto.getAmount());

        repository.updateAccountBalance(targetId, targetBalance);

        repository.flush();
    }

    @Transactional
    public void withdraw(WithdrawDto withdrawDto) throws AccountDoesNotExist{
        Optional<Account> account = repository.findById(withdrawDto.getId());
        if (account.isEmpty()) {
            throw new AccountDoesNotExist("계좌가 존재하지 않습니다.");
        }
        Long targetId = account.get().getId();
        Long targetBalance = account.get().setBalance(-withdrawDto.getAmount());

        repository.updateAccountBalance(targetId, targetBalance);

        repository.flush();
    }
}
