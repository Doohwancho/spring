package com.example.hexagonal.bank;

import com.example.hexagonal.bank.fixture.AccountFixture;
import com.example.hexagonal.dto.deposit.DepositRequest;
import com.example.hexagonal.dto.deposit.DepositResponse;
import com.example.hexagonal.dto.withdraw.WithdrawRequest;
import com.example.hexagonal.dto.withdraw.WithdrawResponse;
import com.example.hexagonal.repository.AccountRepository;
import com.example.hexagonal.service.AccountService;
import com.example.hexagonal.util.DatabaseCleanup;
import com.example.hexagonal.vo.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(AccountIntegrationTest.class);
    public static final long INITIAL_BALANCE = 10000L;

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountFixture accountFixture;

    @Autowired
    private AccountRepository repository;

    private Account account;


    @BeforeEach
    void beforeAll() {
        databaseCleanup.afterPropertiesSet();
        databaseCleanup.execute();

        account = accountFixture.계정준비(); //@AfterEach로 account = null; 해주는게 좋을까 ? 아니면 코드 수 늘어나서 안좋나?
    }


    @Test
    void 입금_요청() throws Exception {
        //given
        Long DEPOSIT_AMOUNT = 10000L;
        Long expected = account.getBalance() + DEPOSIT_AMOUNT;
        DepositRequest request = DepositRequest.of(account.getId(), account.getOwnerName(), DEPOSIT_AMOUNT);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/account/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        DepositResponse depositResponse = objectMapper.readValue(response.getContentAsString(), DepositResponse.class);
        Assertions.assertThat(depositResponse.getDepositAmount()).isEqualByComparingTo(request.getDepositAmount());
        Assertions.assertThat(depositResponse.getOwner()).isEqualTo(request.getOwner());
        Assertions.assertThat(depositResponse.getBalance()).isEqualByComparingTo(expected);
    }


    @Test
    void 출금_요청() throws Exception {
        //given
        Long WITHDRAW_BALANCE = 10000L;
        Long expected = account.getBalance() - WITHDRAW_BALANCE;
        WithdrawRequest request = WithdrawRequest.of(account.getId(), account.getOwnerName(),  WITHDRAW_BALANCE);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/account/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        WithdrawResponse withdrawResponse = objectMapper.readValue(response.getContentAsString(), WithdrawResponse.class);
        Assertions.assertThat(withdrawResponse.getWithdrawAmount()).isEqualByComparingTo(request.getWithdrawAmount());
        Assertions.assertThat(withdrawResponse.getOwner()).isEqualTo(request.getOwner());
        Assertions.assertThat(withdrawResponse.getBalance()).isEqualByComparingTo(expected);
    }
}