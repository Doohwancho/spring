package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import com.example.hexagonal.bank.domain.Bank;

import lombok.Getter;

@Getter
public class DepositAmount {
	private BigDecimal depositAmount;
	private BigDecimal balance;

	private String customer;

	public DepositAmount(Bank newAccount) {
		this.depositAmount = newAccount.getAmount();
		this.balance = newAccount.getBalance();
		this.customer = newAccount.getCustomer();
	}
}
