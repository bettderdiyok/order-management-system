package com.betul.oms.application.usecase;

import java.util.UUID;

public interface PayOrderUseCase {
    void execute(UUID orderId);
}
