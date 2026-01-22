package com.betul.oms.application.usecase.order.pay;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record PayOrderResult(
        UUID id,
        OrderStatus status
) {}
