package com.betul.oms.application.usecase;

import java.util.UUID;

public record CreateOrderItemCommand( //Usecase input
        UUID productId,
        int quantity
) {}
