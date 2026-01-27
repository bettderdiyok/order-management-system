package com.betul.oms.application.usecase.order.ship;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record ShipOrderResult(
        UUID id,
        OrderStatus status
) {}
