package com.betul.oms.api.response;

import java.util.UUID;

public record CancelOrderResponse(
        UUID id,
        String status
) {}
