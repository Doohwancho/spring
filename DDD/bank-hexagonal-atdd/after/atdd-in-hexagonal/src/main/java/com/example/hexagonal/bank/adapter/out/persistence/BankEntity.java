package com.example.hexagonal.bank.adapter.out.persistence;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.hexagonal.bank.domain.Balance;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankEntity {
	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal amount;

	private BigDecimal balance;

	private String customer;


	public BankEntity(BigDecimal amount, String customer, BigDecimal balance) {
		this.amount = amount;
		this.customer = customer;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Balance getBalance() {
		return new Balance(balance);
	}

	public String getCustomer() {
		return customer;
	}
}
