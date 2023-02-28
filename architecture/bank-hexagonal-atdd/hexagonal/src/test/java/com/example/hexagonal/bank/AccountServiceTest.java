package com.example.hexagonal.bank;

import com.example.hexagonal.dto.deposit.DepositRequest;
import com.example.hexagonal.dto.deposit.DepositResponse;
import com.example.hexagonal.dto.withdraw.WithdrawRequest;
import com.example.hexagonal.dto.withdraw.WithdrawResponse;
import com.example.hexagonal.exception.CannotWithdrawBalanceIsBelowZero;
import com.example.hexagonal.repository.AccountRepository;
import com.example.hexagonal.service.AccountService;
import com.example.hexagonal.vo.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTest {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceTest.class);
    public static final long INITIAL_BALANCE = 10000L;

    @Autowired
    private AccountService service;
    @Autowired
    private AccountRepository repository;

    private Account account;

    @BeforeAll
    void beforeAll() {
        repository.deleteAll();

        account = repository.save(Account.builder()
                .ownerName("cho")
                .balance(INITIAL_BALANCE)
                .build());
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
     * a. 은행 계좌에 돈을 입금할 수 있다. -- checked
     *  a-1. 입금은 한번에 한번씩만 되어야 한다.(동시에 두번 이상의 입금처리가 되면 안된다.)
     * b. 은행 계좌에 돈을 출금할 수 있다. -- checked
     *  b-1. 출금 시, 계좌에 있는 돈 이상 출금할 수 없다. -- checked
     *
     * 용어 정의
     *
     * 1. 계좌에 돈을 입금: deposit
     * 2. 계좌에서 돈을 출금: withdraw
     *
     */

    @Test 
    @DisplayName("은행 계좌에 입금 가능해야 한다.")
    void shouldBeAbleToDepositTest() {
        //given
        Long DEPOSIT_AMOUNT = 10000L;
        long expected = account.getBalance() + DEPOSIT_AMOUNT;
        DepositRequest depositRequest = new DepositRequest(account.getId(), account.getOwnerName(), DEPOSIT_AMOUNT);

        //when
        DepositResponse response = service.deposit(depositRequest);

        //then
        Optional<Account> targetAccount = repository.findById(account.getId());
        Assertions.assertThat(targetAccount).isNotEmpty();
        Assertions.assertThat(targetAccount.get().getBalance()).isEqualTo(expected);
    }

    @Test 
    @DisplayName("은행 계좌에서 인출 가능해야 한다.")
    void shouldBeAbleToWithdraw() {
        //given
        Long WITHDRAW_AMOUNT = 10000L;
        long expected = account.getBalance() - WITHDRAW_AMOUNT;
        WithdrawRequest request = new WithdrawRequest(account.getId(), account.getOwnerName(), WITHDRAW_AMOUNT);

        //when
        WithdrawResponse withdraw = service.withdraw(request);

        //then
        Optional<Account> targetAccount = repository.findById(account.getId());
        Assertions.assertThat(targetAccount).isNotEmpty();
        Assertions.assertThat(targetAccount.get().getBalance()).isEqualTo(expected);
    }

    @Test
    @DisplayName("계좌에 가진 돈 이상을 인출시, throw Exception")
    void shouldNotBeAbleToWithdrawMoreThanYouHave() {
        //given
        Long WITHDRAW_AMOUNT = 20000L;
        WithdrawRequest request = new WithdrawRequest(account.getId(), account.getOwnerName(), WITHDRAW_AMOUNT);

        //when & then
        Assertions.assertThatThrownBy(() -> {
            service.withdraw(request);
        }).isInstanceOf(CannotWithdrawBalanceIsBelowZero.class)
        .hasMessageContaining("본인이 가진 액수 이상으로 출금할 수 없습니다.");
    }
}
