package com.kata.orderinhexagonal.stock.adapter.in.web;

import com.kata.orderinhexagonal.stock.application.port.in.*;
import com.kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockInUsecase stockInUsecase;
    private final StockOutUsecase stockOutUsecase;

    @PostMapping("/in")
    @ResponseStatus(HttpStatus.CREATED)
    public StockInResponse stockIn(@RequestBody @Valid StockInRequest request) {
        Stock stock = stockInUsecase.stockIn(request);
        return new StockInResponse(stock.getId(), stock.getItem().getId(), stock.getQuantity(), stock.getItem().getName());
    }


    @PostMapping("/out")
    @ResponseStatus(HttpStatus.OK)
    public StockOutResponse stockOut(@RequestBody @Valid StockOutRequest request) {
        Stock stock = stockOutUsecase.stockOut(request);
        return new StockOutResponse(stock.getId(), stock.getItem().getId(), stock.getQuantity(), stock.getItem().getName());
    }

}
