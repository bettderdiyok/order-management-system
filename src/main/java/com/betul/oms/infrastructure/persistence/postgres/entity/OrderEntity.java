package com.betul.oms.infrastructure.persistence.postgres.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Getter
    @Id
    private UUID id;

    @Column(nullable = false, length = 32)
    @Getter
    private String status;

    public OrderEntity(UUID id, String status) {
        this.id = id;
        this.status = status;
    }

    protected OrderEntity() {

    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    public void addItem(UUID productId, int quantity) {
        items.add(new OrderItemEntity(this, productId, quantity));
    }
}
