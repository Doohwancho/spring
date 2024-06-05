package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import com.example.hexagonal.bank.domain.Bank;

import lombok.Getter;

@Getter
public class WithDrawAmount {
	private BigDecimal withDrawAmount;
	private String customer;
	private BigDecimal balance;

	public WithDrawAmount(Bank newBank) {
		this.withDrawAmount = newBank.getAmount();
		this.customer = newBank.getCustomer();
		this.balance = newBank.getBalance();
	}
}
