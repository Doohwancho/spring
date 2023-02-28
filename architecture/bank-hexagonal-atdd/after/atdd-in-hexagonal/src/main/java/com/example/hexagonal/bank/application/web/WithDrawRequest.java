package com.example.hexagonal.bank.application.web;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class WithDrawRequest {
	private BigDecimal withDrawAmount;
	private String customer;

	public WithDrawRequest(BigDecimal withDrawAmount, String customer) {
		this.withDrawAmount = withDrawAmount;
		this.customer = customer;
	}

	public static WithDrawRequest of(BigDecimal withDrawAmount, String customer) {
		return new WithDrawRequest(withDrawAmount, customer);
	}
}
