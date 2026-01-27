package com.betul.oms.api.controller;

import com.betul.oms.api.mapper.CreateOrderApiMapper;
import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.*;
import com.betul.oms.application.usecase.order.cancel.CancelOrderResult;
import com.betul.oms.application.usecase.order.cancel.CancelOrderUseCase;
import com.betul.oms.application.usecase.order.create.CreateOrderResult;
import com.betul.oms.application.usecase.order.create.CreateOrderUseCase;
import com.betul.oms.application.usecase.order.deliver.DeliverOrderResult;
import com.betul.oms.application.usecase.order.deliver.DeliverOrderUseCase;
import com.betul.oms.application.usecase.order.get.GetOrderResult;
import com.betul.oms.application.usecase.order.get.GetOrderUseCase;
import com.betul.oms.application.usecase.order.pay.PayOrderResult;
import com.betul.oms.application.usecase.order.pay.PayOrderUseCase;
import com.betul.oms.application.usecase.order.prepare.PrepareOrderResult;
import com.betul.oms.application.usecase.order.prepare.PrepareOrderUseCase;
import com.betul.oms.application.usecase.order.ship.ShipOrderResult;
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
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResult result = createOrderUseCase.execute(
                CreateOrderApiMapper.toCreateOrderCommand(createOrderRequest)
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateOrderApiMapper.toCreateOrderResponse(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getById(@PathVariable UUID id) {
        GetOrderResult result = getOrderUseCase.execute(id);
        return ResponseEntity.ok(new GetOrderResponse(result.orderId(), result.status(), result.orderItem()));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<PayOrderResponse> pay(@PathVariable UUID id) {
        PayOrderResult result = payOrderUseCase.execute(id);
        return ResponseEntity.ok(new PayOrderResponse(
                result.id(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/prepare")
    public ResponseEntity<PrepareOrderResponse> prepare(@PathVariable UUID id) {
        PrepareOrderResult result = prepareOrderUseCase.execute(id);
        return ResponseEntity.ok(new PrepareOrderResponse(
                result.id(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/ship")
    public ResponseEntity<ShipOrderResponse> ship(@PathVariable UUID id) {
        ShipOrderResult result = shipOrderUseCase.execute(id);
        return ResponseEntity.ok( new ShipOrderResponse(
                result.id(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<DeliverOrderResponse> deliver(@PathVariable UUID id) {
        DeliverOrderResult result = deliverOrderUseCase.execute(id);
        return ResponseEntity.ok( new DeliverOrderResponse(
                result.id(),
                result.status().name()
        ));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<CancelOrderResponse> cancel(@PathVariable UUID id) {
        CancelOrderResult result = cancelOrderUseCase.execute(id);
        return ResponseEntity.ok( new CancelOrderResponse(
                result.id(),
                result.status().name()
        ));
    }




}
