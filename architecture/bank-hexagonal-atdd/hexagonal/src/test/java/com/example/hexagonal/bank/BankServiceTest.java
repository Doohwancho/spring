package com.example.hexagonal.bank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankServiceTest {

    private static final Logger log = LoggerFactory.getLogger(BankServiceTest.class);

    @Autowired
    private AccountService service;
    @Autowired
    private AccountRepository repository;

    @BeforeAll
    void beforeAll() {
        repository.deleteAll();
    }

    @AfterAll
    void afterAll() {
        repository.deleteAll();
    }

    /**
     * Acceptance Criteria
     *
     * 요구사항 정의
     *
     * a. 은행 계좌에 돈을 입금할 수 있다.
     *  a-1. 입금은 한번에 한번씩만 되어야 한다.(동시에 두번 이상의 입금처리가 되면 안된다.)
     * b. 은행 계좌에 돈을 출금할 수 있다.
     *  b-1. 출금 시, 계좌에 있는 돈 이상 출금할 수 없다.
     *
     * 용어 정의
     *
     * 1. 계좌에 돈을 입금: deposit
     * 2. 계좌에서 돈을 출금: withdraw
     *
     */

    @Test 
    void shouldBeAbleToDepositTest() {
        //given
        Long prevBalance = 0L;
        Account account = Account.builder()
                .ownerName("cho")
                .balance(prevBalance)
                .build();
        Account prevAccount = repository.save(account);

        long expected = prevAccount.getBalance() + 10000L;
        DepositDto depositDto = new DepositDto(prevAccount.getId(), expected);


        //when
        service.deposit(depositDto);

        //then
        Optional<Account> targetAccount = repository.findById(prevAccount.getId());
        Assertions.assertThat(targetAccount).isNotEmpty();
        Assertions.assertThat(targetAccount.get().getBalance()).isEqualTo(expected);
    }

}
