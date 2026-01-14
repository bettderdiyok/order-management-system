package com.betul.oms.application.usecase;

import com.betul.oms.domain.model.OrderItem;

import java.util.List;
import java.util.UUID;

public record GetOrderResult(
        UUID orderId,
        String status,
        List<OrderItem> orderItem
) {}
