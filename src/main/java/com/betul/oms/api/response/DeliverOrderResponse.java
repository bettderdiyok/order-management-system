package com.betul.oms.api.response;

import java.util.UUID;

public record DeliverOrderResponse(
        UUID uuid,
        String status
) {}
