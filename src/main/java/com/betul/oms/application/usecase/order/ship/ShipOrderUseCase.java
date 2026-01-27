package com.betul.oms.application.usecase.order.ship;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

import java.util.UUID;

public interface ShipOrderUseCase {
    OrderActionResult execute(UUID id);
}
