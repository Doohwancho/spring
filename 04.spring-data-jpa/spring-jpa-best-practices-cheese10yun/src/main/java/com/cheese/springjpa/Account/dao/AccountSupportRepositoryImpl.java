package com.cheese.springjpa.Account.dao;

import com.cheese.springjpa.Account.dao.AccountSupportRepository;
import com.cheese.springjpa.Account.domain.Account;
import com.cheese.springjpa.Account.domain.QAccount;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class AccountSupportRepositoryImpl extends QuerydslRepositorySupport implements AccountSupportRepository {

    public AccountSupportRepositoryImpl() {
        super(Account.class);
    }

    @Override
    public List<Account> findRecentlyRegistered(int limit) {
        final QAccount account = QAccount.account;
        return from(account)
                .limit(limit)
                .orderBy(account.createdAt.desc())
                .fetch();
    }
}