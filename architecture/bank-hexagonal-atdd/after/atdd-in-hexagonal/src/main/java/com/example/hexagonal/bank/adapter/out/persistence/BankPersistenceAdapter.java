package com.example.hexagonal.bank.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.hexagonal.bank.application.port.out.LoadCustomerPort;
import com.example.hexagonal.bank.application.port.out.SaveBankPort;
import com.example.hexagonal.bank.domain.Bank;

@Repository
public class BankPersistenceAdapter implements SaveBankPort, LoadCustomerPort {

	private final BankAmountSpringDataRepository repository;
	private final BankAmountMapper mapper;

	public BankPersistenceAdapter(BankAmountSpringDataRepository repository, BankAmountMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Bank save(Bank bank) {
		BankEntity entity = mapper.toEntity(bank);
		BankEntity saveEntity = repository.save(entity);
		return mapper.toDomain(saveEntity);
	}

	@Override
	public BigDecimal getBalance(String customer) {
		Optional<BigDecimal> balance = repository.getBalance(customer);
		return balance.orElse(BigDecimal.ZERO);
	}
}
