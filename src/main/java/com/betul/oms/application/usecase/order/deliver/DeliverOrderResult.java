package com.betul.oms.application.usecase.order.deliver;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record DeliverOrderResult(
        UUID id,
        OrderStatus status
) {}
