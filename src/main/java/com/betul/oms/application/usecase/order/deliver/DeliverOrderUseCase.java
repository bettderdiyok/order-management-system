package com.betul.oms.application.usecase.order.deliver;

import java.util.UUID;

public interface DeliverOrderUseCase {
    DeliverOrderResult execute(UUID orderId);

}
