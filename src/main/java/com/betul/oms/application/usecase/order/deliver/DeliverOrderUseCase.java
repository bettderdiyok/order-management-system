package com.betul.oms.application.usecase.order.deliver;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

import java.util.UUID;

public interface DeliverOrderUseCase {
    OrderActionResult execute(UUID orderId);

}
