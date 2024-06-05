package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithDrawResponse {
	private BigDecimal withDrawAmount;
	private String customer;
	private BigDecimal balance;

	public WithDrawResponse(WithDrawAmount withDrawAmount) {
		this.withDrawAmount = withDrawAmount.getWithDrawAmount();
		this.customer = withDrawAmount.getCustomer();
		this.balance = withDrawAmount.getBalance();
	}
}
