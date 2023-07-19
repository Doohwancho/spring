package com.example.hexagonal.bank.application.port.in;

import com.example.hexagonal.bank.application.web.DepositAmount;
import com.example.hexagonal.bank.application.web.DepositRequest;

public interface DepositUseCase {
	DepositAmount deposit(DepositRequest request);
}
