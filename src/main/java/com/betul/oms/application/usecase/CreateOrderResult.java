package com.betul.oms.application.usecase;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record CreateOrderResult( //output
        UUID orderId,
        OrderStatus status
) {}
