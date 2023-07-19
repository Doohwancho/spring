package com.example.hexagonal.bank.application.port.in;

import com.example.hexagonal.bank.application.web.WithDrawAmount;
import com.example.hexagonal.bank.application.web.WithDrawRequest;

public interface WithDrawUseCase {
	WithDrawAmount withDraw(WithDrawRequest request);
}
