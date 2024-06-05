package com.kata.orderinhexagonal.discount.adapter.in.web;

import com.kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountRequest;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountResponse;
import com.kata.orderinhexagonal.discount.domain.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
   private final CreateItemDiscountUsecase createItemDiscountUsecase;

   @PostMapping("/items")
   @ResponseStatus(HttpStatus.CREATED)
   public ItemDiscountResponse createItemDiscount(@RequestBody @Valid ItemDiscountRequest request) {
       Discount discount = createItemDiscountUsecase.applyDiscount(request);
       return ItemDiscountResponse.of(discount);
   }
}
