package com.betul.oms.api.response;

import com.betul.oms.domain.model.OrderStatus;

import java.util.UUID;

public record PayOrderResponse(
        UUID id,
        String status
) {
}
