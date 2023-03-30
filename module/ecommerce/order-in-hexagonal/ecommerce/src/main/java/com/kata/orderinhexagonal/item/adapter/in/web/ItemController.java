package com.kata.orderinhexagonal.item.adapter.in.web;

import com.kata.orderinhexagonal.item.application.port.in.CreateItemResponse;
import com.kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final CreateItemUsecase createItemUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateItemResponse createItem(@RequestBody @Valid com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest request, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }
        Item item = createItemUsecase.createItem(request);
        return new CreateItemResponse(item);
    }
}