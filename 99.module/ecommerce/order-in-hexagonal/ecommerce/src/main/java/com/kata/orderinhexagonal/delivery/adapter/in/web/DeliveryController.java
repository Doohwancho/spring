package com.kata.orderinhexagonal.delivery.adapter.in.web;

import com.kata.orderinhexagonal.delivery.application.port.in.CreateDeliveryUsecase;
import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryResponse;
import com.kata.orderinhexagonal.delivery.domain.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final CreateDeliveryUsecase createDeliveryUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public DeliveryResponse delivery(@RequestBody @Valid DeliveryRequest request, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }

        Delivery delivery = createDeliveryUsecase.create(request);

        return new DeliveryResponse(delivery);
    }
}
