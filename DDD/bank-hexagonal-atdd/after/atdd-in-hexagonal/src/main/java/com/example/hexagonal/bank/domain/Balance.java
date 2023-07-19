package com.example.hexagonal.bank.domain;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Balance {
	private BigDecimal balance;

	public Balance(BigDecimal amount) {
		balance = amount;
	}

	public boolean isGreaterThan(BigDecimal amount) {
		return balance.compareTo(amount) >= 0;
	}

	public Balance add(BigDecimal amount) {
		return new Balance(balance.add(amount));
	}

	public Balance subtract(BigDecimal amount) {
		if(!isGreaterThan(amount)) {
			throw new IllegalArgumentException("Not enough balance");
		}
		return new Balance(balance.subtract(amount));
	}

	public BigDecimal value() {
		return balance;
	}
}
