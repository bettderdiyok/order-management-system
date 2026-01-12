package com.betul.oms.api.controller;

import com.betul.oms.api.mapper.CreateOrderApiMapper;
import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.CreateOrderResponse;
import com.betul.oms.application.usecase.CreateOrderResult;
import com.betul.oms.application.usecase.CreateOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        //throw new RuntimeException("boom");
        CreateOrderResult result = createOrderUseCase.create(
                CreateOrderApiMapper.toCreateOrderCommand(createOrderRequest)
        );

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateOrderApiMapper.toCreateOrderResponse(result));


    }
}
