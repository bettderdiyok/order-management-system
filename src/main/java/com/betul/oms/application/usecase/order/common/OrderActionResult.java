package com.betul.oms.application.usecase.order.common;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record OrderActionResult(
        UUID orderId,
        OrderStatus status

) {}
