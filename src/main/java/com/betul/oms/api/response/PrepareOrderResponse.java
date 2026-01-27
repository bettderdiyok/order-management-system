package com.betul.oms.api.response;

import java.util.UUID;

public record PrepareOrderResponse(
        UUID id,
        String status
) {}
