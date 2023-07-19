package com.example.hexagonal.bank.application.port.out;

import com.example.hexagonal.bank.domain.Bank;

public interface SaveBankPort {
	Bank save(Bank amount);
}
