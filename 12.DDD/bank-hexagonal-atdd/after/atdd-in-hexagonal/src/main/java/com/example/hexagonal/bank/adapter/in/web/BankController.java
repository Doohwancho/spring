package com.example.hexagonal.bank.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hexagonal.bank.application.web.DepositAmount;
import com.example.hexagonal.bank.application.web.DepositRequest;
import com.example.hexagonal.bank.application.web.DepositResponse;
import com.example.hexagonal.bank.application.web.WithDrawAmount;
import com.example.hexagonal.bank.application.web.WithDrawRequest;
import com.example.hexagonal.bank.application.web.WithDrawResponse;
import com.example.hexagonal.bank.application.port.in.DepositUseCase;
import com.example.hexagonal.bank.application.port.in.WithDrawUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BankController {

	private final DepositUseCase depositUseCase;
	private final WithDrawUseCase withDrawUseCase;

	@PostMapping("/deposit")
	public DepositResponse deposit(@RequestBody DepositRequest request) {
		DepositAmount depositAmount = depositUseCase.deposit(request);
		DepositResponse depositResponse = new DepositResponse(depositAmount);
		return depositResponse;
	}

	@PostMapping("/withdraw")
	public WithDrawResponse withdraw(@RequestBody WithDrawRequest request) {
		WithDrawAmount withDrawAmount = withDrawUseCase.withDraw(request);
		WithDrawResponse withDrawResponse = new WithDrawResponse(withDrawAmount);
		return withDrawResponse;
	}

}
