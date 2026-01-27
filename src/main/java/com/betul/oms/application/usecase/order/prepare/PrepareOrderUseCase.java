package com.betul.oms.application.usecase.order.prepare;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

import java.util.UUID;

public interface PrepareOrderUseCase {
   OrderActionResult execute(UUID orderId);
}
