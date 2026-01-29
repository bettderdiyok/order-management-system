package com.betul.oms.api.controller.order;

import com.betul.oms.api.mapper.GetOrderApiMapper;
import com.betul.oms.api.response.GetOrderResponse;
import com.betul.oms.application.usecase.order.get.GetOrderResult;
import com.betul.oms.application.usecase.order.get.GetOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    private final GetOrderUseCase getOrderUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getById(@PathVariable UUID id) {
        GetOrderResult result = getOrderUseCase.execute(id);

        return ResponseEntity.ok(GetOrderApiMapper.toGetOrderResponse(result));
    }
}
