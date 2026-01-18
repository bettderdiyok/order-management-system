package com.betul.oms.application.usecase.order.get;

import java.util.UUID;

public interface GetOrderUseCase {
    GetOrderResult getById(UUID id);
}
