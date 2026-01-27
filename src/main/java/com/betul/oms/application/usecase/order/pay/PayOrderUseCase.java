package com.betul.oms.application.usecase.order.pay;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

import java.util.UUID;

public interface PayOrderUseCase {
    OrderActionResult execute(UUID orderId);
}
