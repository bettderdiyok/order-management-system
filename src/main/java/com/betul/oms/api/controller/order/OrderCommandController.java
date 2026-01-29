package com.betul.oms.api.controller.order;

import com.betul.oms.api.mapper.CreateOrderApiMapper;
import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.OrderActionResponse;
import com.betul.oms.application.usecase.order.cancel.CancelOrderUseCase;
import com.betul.oms.application.usecase.order.common.OrderActionResult;
import com.betul.oms.application.usecase.order.create.CreateOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final CreateOrderUseCase createOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderActionResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderActionResult result = createOrderUseCase.execute(
                CreateOrderApiMapper.toCreateOrderCommand(createOrderRequest)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateOrderApiMapper.toCreateOrderResponse(result));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderActionResponse> cancel(@PathVariable UUID id) {
        OrderActionResult result = cancelOrderUseCase.execute(id);
        return ResponseEntity.ok( new OrderActionResponse(
                result.orderId(),
                result.status().name()
        ));
    }
}
