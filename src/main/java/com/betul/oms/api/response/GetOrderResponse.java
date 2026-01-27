package com.betul.oms.api.response;

import java.util.List;
import java.util.UUID;

public record GetOrderResponse(
        UUID orderId,
        String status,
        List<OrderItemResponse> items
) {}
