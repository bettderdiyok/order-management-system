package com.betul.oms.infrastructure.persistence.postgres.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @EmbeddedId
    @Getter
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(nullable = false)
    @Getter
    private int quantity;

    protected OrderItemEntity() {}

    public OrderItemEntity(OrderEntity order, UUID productId, int quantity) {
        this.order = order;
        this.id = new OrderItemId(order.getId(), productId);
        this.quantity = quantity;
    }
}
