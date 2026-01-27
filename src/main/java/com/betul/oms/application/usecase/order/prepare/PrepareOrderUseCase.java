package com.betul.oms.application.usecase.order.prepare;

import java.util.UUID;

public interface PrepareOrderUseCase {
   PrepareOrderResult execute(UUID orderId);
}
