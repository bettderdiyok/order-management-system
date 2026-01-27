package com.betul.oms.application.usecase.order.cancel;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record CancelOrderResult(
        UUID id,
        OrderStatus status
) {}
