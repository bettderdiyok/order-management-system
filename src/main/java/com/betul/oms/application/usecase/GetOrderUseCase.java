package com.betul.oms.application.usecase;

import java.util.UUID;

public interface GetOrderUseCase {
    GetOrderResult getById(UUID id);
}
