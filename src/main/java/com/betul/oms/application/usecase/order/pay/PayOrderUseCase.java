package com.betul.oms.application.usecase.order.pay;

import java.util.UUID;

public interface PayOrderUseCase {
    void execute(UUID orderId);
}
