package com.betul.oms.api.controller.order;

import com.betul.oms.api.response.OrderActionResponse;
import com.betul.oms.application.usecase.order.common.OrderActionResult;
import com.betul.oms.application.usecase.order.deliver.DeliverOrderUseCase;
import com.betul.oms.application.usecase.order.pay.PayOrderUseCase;
import com.betul.oms.application.usecase.order.prepare.PrepareOrderUseCase;
import com.betul.oms.application.usecase.order.ship.ShipOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderWorkflowController {

    private final PayOrderUseCase payOrderUseCase;
    private final PrepareOrderUseCase prepareOrderUseCase;
    private final ShipOrderUseCase shipOrderUseCase;
    private final DeliverOrderUseCase deliverOrderUseCase;

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
}
