package com.betul.oms.api.controller;

import com.betul.oms.api.mapper.CreateOrderApiMapper;
import com.betul.oms.api.mapper.GetOrderApiMapper;
import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.*;
import com.betul.oms.application.usecase.order.cancel.CancelOrderUseCase;
import com.betul.oms.application.usecase.order.common.OrderActionResult;
import com.betul.oms.application.usecase.order.create.CreateOrderUseCase;
import com.betul.oms.application.usecase.order.deliver.DeliverOrderUseCase;
import com.betul.oms.application.usecase.order.get.GetOrderResult;
import com.betul.oms.application.usecase.order.get.GetOrderUseCase;
import com.betul.oms.application.usecase.order.pay.PayOrderUseCase;
import com.betul.oms.application.usecase.order.prepare.PrepareOrderUseCase;
import com.betul.oms.application.usecase.order.ship.ShipOrderUseCase;
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
    private final PrepareOrderUseCase prepareOrderUseCase;
    private final ShipOrderUseCase shipOrderUseCase;
    private final DeliverOrderUseCase deliverOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase, PayOrderUseCase payOrderUseCase, PrepareOrderUseCase prepareOrderUseCase, ShipOrderUseCase shipOrderUseCase, DeliverOrderUseCase deliverOrderUseCase, CancelOrderUseCase cancelOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.payOrderUseCase = payOrderUseCase;
        this.prepareOrderUseCase = prepareOrderUseCase;
        this.shipOrderUseCase = shipOrderUseCase;
        this.deliverOrderUseCase = deliverOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderActionResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderActionResult result = createOrderUseCase.execute(
                CreateOrderApiMapper.toCreateOrderCommand(createOrderRequest)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateOrderApiMapper.toCreateOrderResponse(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getById(@PathVariable UUID id) {
        GetOrderResult result = getOrderUseCase.execute(id);

        return ResponseEntity.ok(GetOrderApiMapper.toGetOrderResponse(result));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<OrderActionResponse> pay(@PathVariable UUID id) {
        OrderActionResult result = payOrderUseCase.execute(id);
        return ResponseEntity.ok(new OrderActionResponse(
                result.orderId(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/prepare")
    public ResponseEntity<OrderActionResponse> prepare(@PathVariable UUID id) {
        OrderActionResult result = prepareOrderUseCase.execute(id);
        return ResponseEntity.ok(new OrderActionResponse(
                result.orderId(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/ship")
    public ResponseEntity<OrderActionResponse> ship(@PathVariable UUID id) {
        OrderActionResult result = shipOrderUseCase.execute(id);
        return ResponseEntity.ok( new OrderActionResponse(
                result.orderId(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<OrderActionResponse> deliver(@PathVariable UUID id) {
        OrderActionResult result = deliverOrderUseCase.execute(id);
        return ResponseEntity.ok( new OrderActionResponse(
                result.orderId(),
                result.status().name()
        ));
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
