package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepositRequest {

	private BigDecimal depositAmount;
	private String customer;

	public DepositRequest(BigDecimal depositAmount, String customer) {
		this.depositAmount = depositAmount;
		this.customer = customer;
	}

	public static DepositRequest of(BigDecimal depositAmount, String customer) {
		return new DepositRequest(depositAmount, customer);
	}
}
