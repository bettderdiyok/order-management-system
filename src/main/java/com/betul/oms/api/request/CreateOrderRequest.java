package com.betul.oms.api.request;

import jakarta.validation.Valid;

import java.util.List;

public record CreateOrderRequest(
        List<@Valid CreateOrderItemRequest>  items
) {}
