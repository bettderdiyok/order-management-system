package com.betul.oms.infrastructure.persistence.postgres.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class OrderItemId implements Serializable {
    private UUID orderId;
    private UUID productId;

    protected OrderItemId(){}

    public OrderItemId(UUID orderId, UUID productId) {
        this.orderId = orderId;
        this.productId = productId;
    }
}
