package com.example.hexagonal.bank.domain;

import java.math.BigDecimal;

import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
	private BigDecimal amount;
	private String customer;
	@Embedded
	private Balance balance;

	public Bank(String customer, Balance balance) {
		this.customer = customer;
		this.balance = balance;
	}

	public void deposit(BigDecimal depositAmount) {
		this.amount = depositAmount;
		this.balance = balance.add(amount);
	}

	public void withDraw(BigDecimal withDrawAmount) {
		this.amount = withDrawAmount;
		this.balance = this.balance.subtract(amount);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCustomer() {
		return customer;
	}

	public BigDecimal getBalance() {
		return balance.value();
	}
}
