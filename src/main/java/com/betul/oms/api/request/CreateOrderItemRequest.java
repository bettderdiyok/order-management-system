package com.betul.oms.api.request;

import java.util.UUID;

public record CreateOrderItemRequest(
        UUID productId,
        int quantity
) {}
