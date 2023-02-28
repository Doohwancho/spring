package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositResponse {

	private BigDecimal depositAmount;
	private String customer;

	private BigDecimal balance;

	public DepositResponse(DepositAmount depositAmount) {
		this.depositAmount = depositAmount.getDepositAmount();
		this.customer = depositAmount.getCustomer();
		this.balance = depositAmount.getBalance();
	}
}
