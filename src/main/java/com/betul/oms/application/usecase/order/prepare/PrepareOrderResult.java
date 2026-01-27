package com.betul.oms.application.usecase.order.prepare;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record PrepareOrderResult(
        UUID id,
        OrderStatus status
) {}
