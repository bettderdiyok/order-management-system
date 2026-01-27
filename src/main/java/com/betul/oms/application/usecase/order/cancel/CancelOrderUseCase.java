package com.betul.oms.application.usecase.order.cancel;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

import java.util.UUID;

public interface CancelOrderUseCase {
    OrderActionResult execute(UUID orderId);
}
