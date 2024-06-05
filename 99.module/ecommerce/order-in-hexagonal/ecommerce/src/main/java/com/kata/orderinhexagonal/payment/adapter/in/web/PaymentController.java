package com.kata.orderinhexagonal.payment.adapter.in.web;

import com.kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import com.kata.orderinhexagonal.payment.application.port.in.PaymentResponse;
import com.kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import com.kata.orderinhexagonal.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentUsecase paymentUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest paymentRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }

        Payment payments = paymentUsecase.payments(paymentRequest);

        return new PaymentResponse(payments.getId(), payments.getOrder().getId(), payments.getOrder().getTotalPrice(), payments.getPaymentType(), payments.getCardType());
    }
}
