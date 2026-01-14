package com.betul.oms.api.response;


import com.betul.oms.domain.model.OrderItem;

import java.util.List;
import java.util.UUID;

public record GetOrderResponse(
        UUID orderId,
        String status,
        List<OrderItem> orderItem
) {}
