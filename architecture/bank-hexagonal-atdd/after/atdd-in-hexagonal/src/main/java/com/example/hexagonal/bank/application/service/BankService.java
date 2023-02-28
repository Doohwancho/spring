package com.example.hexagonal.bank.application.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.hexagonal.bank.application.web.DepositAmount;
import com.example.hexagonal.bank.application.web.WithDrawAmount;
import com.example.hexagonal.bank.application.web.WithDrawRequest;
import com.example.hexagonal.bank.application.port.in.WithDrawUseCase;
import com.example.hexagonal.bank.application.port.out.LoadCustomerPort;
import com.example.hexagonal.bank.application.port.out.SaveBankPort;
import com.example.hexagonal.bank.domain.Balance;
import com.example.hexagonal.bank.domain.Bank;
import com.example.hexagonal.bank.application.web.DepositRequest;
import com.example.hexagonal.bank.application.port.in.DepositUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BankService implements DepositUseCase, WithDrawUseCase {

	private final SaveBankPort saveBankPort;
	private final LoadCustomerPort loadCustomerPort;

	@Override
	public DepositAmount deposit(DepositRequest request) {
		BigDecimal balance = loadCustomerPort.getBalance(request.getCustomer());

		Bank bank = new Bank(request.getCustomer(), new Balance(balance));
		bank.deposit(request.getDepositAmount());
		Bank newAccount = saveBankPort.save(bank);

		return new DepositAmount(newAccount);
	}

	@Override
	public WithDrawAmount withDraw(WithDrawRequest request) {
		BigDecimal balance = loadCustomerPort.getBalance(request.getCustomer());
		Bank bank = new Bank(request.getCustomer(), new Balance(balance));
		bank.withDraw(request.getWithDrawAmount());
		Bank newBank = saveBankPort.save(bank);
		return new WithDrawAmount(newBank);
	}
}
