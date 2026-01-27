package com.betul.oms.api.response;

import java.util.UUID;

public record OrderActionResponse(
        UUID id,
        String status
) {}
