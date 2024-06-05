package com.example.hexagonal.bank.fixture;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hexagonal.bank.application.service.BankService;
import com.example.hexagonal.bank.application.web.DepositAmount;
import com.example.hexagonal.bank.application.web.DepositRequest;
import com.example.hexagonal.bank.application.web.WithDrawAmount;
import com.example.hexagonal.bank.application.web.WithDrawRequest;

@Component
public class BankFixture {

	@Autowired
	BankService bankService;

	public void 입금_요청(int depositAmount, String customer) {
		BigDecimal amount = BigDecimal.valueOf(depositAmount);
		DepositRequest request = DepositRequest.of(amount, customer);
		bankService.deposit(request);
	}

	public void 출금_요청(int withDrawAmount, String customer) {
		BigDecimal withdrawAmountDecimal = BigDecimal.valueOf(withDrawAmount);
		WithDrawRequest withDrawRequest = WithDrawRequest.of(withdrawAmountDecimal, customer);
		bankService.withDraw(withDrawRequest);
	}
}
