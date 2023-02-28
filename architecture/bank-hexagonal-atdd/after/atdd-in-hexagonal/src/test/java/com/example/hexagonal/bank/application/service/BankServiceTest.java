package com.example.hexagonal.bank.application.service;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hexagonal.bank.adapter.out.persistence.BankAmountSpringDataRepository;
import com.example.hexagonal.bank.application.web.DepositAmount;
import com.example.hexagonal.bank.application.web.DepositRequest;
import com.example.hexagonal.bank.application.web.WithDrawAmount;
import com.example.hexagonal.bank.application.web.WithDrawRequest;
import com.example.hexagonal.bank.fixture.BankFixture;

@SpringBootTest
class BankServiceTest {

	@Autowired BankService bankService;

	@Autowired
	BankAmountSpringDataRepository repository;

	@Autowired
	BankFixture bankFixture;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void depositAmountTest() {
		// given
		BigDecimal amount = BigDecimal.valueOf(100);
		String customer = "joongseok";
		DepositRequest request = DepositRequest.of(amount, customer);

		// when
		DepositAmount depositAmount = bankService.deposit(request);
		// then
		Assertions.assertThat(depositAmount.getDepositAmount()).isEqualByComparingTo(request.getDepositAmount());
		Assertions.assertThat(depositAmount.getCustomer()).isEqualTo(customer);
		Assertions.assertThat(depositAmount.getBalance()).isEqualByComparingTo(amount);
	}

	@Test
	void withdrawAmountTest() {
		// given
		String customer = "joongseok";
		bankFixture.입금_요청(100, customer);

		BigDecimal withdrawAmountDecimal = BigDecimal.valueOf(60);
		WithDrawRequest withDrawRequest = WithDrawRequest.of(withdrawAmountDecimal, customer);

		WithDrawAmount withDrawAmount = bankService.withDraw(withDrawRequest);

		Assertions.assertThat(withDrawAmount.getWithDrawAmount()).isEqualByComparingTo(withDrawRequest.getWithDrawAmount());
		Assertions.assertThat(withDrawAmount.getCustomer()).isEqualTo(customer);
		Assertions.assertThat(withDrawAmount.getBalance()).isEqualByComparingTo(new BigDecimal(40));
	}

	@Test
	void shouldWithDrawGreaterThanAmountReturnThrow() {
		// given
		BigDecimal withdrawAmountDecimal = BigDecimal.valueOf(60);
		String customer = "joongseok";
		WithDrawRequest withDrawRequest = WithDrawRequest.of(withdrawAmountDecimal, customer);

		// when
		Assertions.assertThatThrownBy(() -> bankService.withDraw(withDrawRequest))
			.isInstanceOf(IllegalArgumentException.class);

		// then
	}


}
