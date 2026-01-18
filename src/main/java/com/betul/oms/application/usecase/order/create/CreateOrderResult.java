package com.betul.oms.application.usecase.order.create;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record CreateOrderResult( //output
        UUID orderId,
        OrderStatus status

) {}
