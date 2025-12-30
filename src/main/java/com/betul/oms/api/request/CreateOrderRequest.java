package com.betul.oms.api.request;

import java.util.List;

public record CreateOrderRequest(
        List<CreateOrderItemRequest>  items
) {}
