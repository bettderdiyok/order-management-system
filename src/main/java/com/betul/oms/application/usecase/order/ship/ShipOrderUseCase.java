package com.betul.oms.application.usecase.order.ship;

import java.util.UUID;

public interface ShipOrderUseCase {
    ShipOrderResult execute(UUID id);
}
