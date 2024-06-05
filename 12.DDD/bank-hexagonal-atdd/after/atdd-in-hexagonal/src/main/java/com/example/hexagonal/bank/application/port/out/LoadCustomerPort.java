package com.example.hexagonal.bank.application.port.out;

import java.math.BigDecimal;

public interface LoadCustomerPort {
	BigDecimal getBalance(String customer);
}
