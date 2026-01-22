package com.betul.oms.api.response;

import java.util.UUID;

public record PayOrderResponse(
        UUID id,
        String status
) {
}
