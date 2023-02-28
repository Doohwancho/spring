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
    public DepositResponse deposit(DepositRequest request) throws AccountDoesNotExist {
        Optional<Account> account = repository.findById(request.getId());

        if (account.isEmpty()) {
            throw new AccountDoesNotExist("계좌가 존재하지 않습니다.");
        }
        Long targetId = account.get().getId();
        Long targetBalance = account.get().setBalance(request.getDepositAmount());

        repository.updateAccountBalance(targetId, targetBalance);
        repository.flush();

        return new DepositResponse(account.get(), request.getDepositAmount());
    }

    @Transactional
    public WithdrawResponse withdraw(WithdrawRequest request) throws AccountDoesNotExist{
        Optional<Account> account = repository.findById(request.getId());
        if (account.isEmpty()) {
            throw new AccountDoesNotExist("계좌가 존재하지 않습니다.");
        }
        Long targetId = account.get().getId();
        Long targetBalance = account.get().setBalance(-request.getWithdrawAmount());

        repository.updateAccountBalance(targetId, targetBalance);

        repository.flush();

        return new WithdrawResponse(account.get(), request.getWithdrawAmount());
    }
}
