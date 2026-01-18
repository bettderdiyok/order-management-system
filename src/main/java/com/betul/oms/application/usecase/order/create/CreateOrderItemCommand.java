package com.betul.oms.application.usecase.order.create;

import java.util.UUID;

public record CreateOrderItemCommand( //Usecase input
        UUID productId,
        int quantity
) {}
