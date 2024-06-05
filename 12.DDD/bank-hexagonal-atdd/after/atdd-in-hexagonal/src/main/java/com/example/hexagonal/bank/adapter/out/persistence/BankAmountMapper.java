package com.example.hexagonal.bank.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.example.hexagonal.bank.domain.Bank;

@Component
public class BankAmountMapper {

	public Bank toDomain(BankEntity entity) {
		return Bank.builder()
			.amount(entity.getAmount())
			.customer(entity.getCustomer())
			.balance(entity.getBalance())
			.build();
	}

	public BankEntity toEntity(Bank domain) {
		return new BankEntity(domain.getAmount(), domain.getCustomer(), domain.getBalance());
	}
}
