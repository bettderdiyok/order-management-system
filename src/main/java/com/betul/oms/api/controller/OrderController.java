package com.betul.oms.api.controller;

import com.betul.oms.api.mapper.CreateOrderApiMapper;
import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.CreateOrderResponse;
import com.betul.oms.api.response.GetOrderResponse;
import com.betul.oms.api.response.PayOrderResponse;
import com.betul.oms.application.usecase.CreateOrderResult;
import com.betul.oms.application.usecase.CreateOrderUseCase;
import com.betul.oms.application.usecase.GetOrderResult;
import com.betul.oms.application.usecase.GetOrderUseCase;
import com.betul.oms.application.usecase.PayOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final PayOrderUseCase payOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, PayOrderUseCase payOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.payOrderUseCase = payOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResult result = createOrderUseCase.create(
                CreateOrderApiMapper.toCreateOrderCommand(createOrderRequest)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateOrderApiMapper.toCreateOrderResponse(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getById(@PathVariable UUID id) {
        GetOrderResult result = getOrderUseCase.getById(id);
        return ResponseEntity.ok(new GetOrderResponse(result.orderId(), result.status(), result.orderItem()));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<PayOrderResponse> pay(@PathVariable UUID id) {
        payOrderUseCase.execute(id);
        GetOrderResult result = getOrderUseCase.getById(id);
        return ResponseEntity.ok(new PayOrderResponse(
                result.orderId(),
                result.status()
        ));
    }
}
