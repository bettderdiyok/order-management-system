package com.betul.oms.application.usecase.order.pay;

import java.util.UUID;

public interface PayOrderUseCase {
    PayOrderResult execute(UUID orderId);
}
