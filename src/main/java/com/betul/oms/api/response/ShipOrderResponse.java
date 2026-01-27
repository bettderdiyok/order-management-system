package com.betul.oms.api.response;

import java.util.UUID;

public record ShipOrderResponse(
        UUID uuid,
        String status
){
}
