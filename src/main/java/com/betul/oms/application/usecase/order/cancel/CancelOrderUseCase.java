package com.betul.oms.application.usecase.order.cancel;

import java.util.UUID;

public interface CancelOrderUseCase {
    CancelOrderResult execute(UUID orderId);
}
