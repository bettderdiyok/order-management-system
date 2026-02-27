package com.betul.oms.infrastructure.persistence.postgres.mapper;

import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.model.OrderItem;
import com.betul.oms.domain.model.OrderStatus;
import com.betul.oms.infrastructure.persistence.postgres.entity.OrderEntity;
import com.betul.oms.infrastructure.persistence.postgres.entity.OrderItemEntity;

import java.util.List;
import java.util.UUID;

public final class OrderMapper {

    private OrderMapper(){}

    public static OrderEntity toOrderEntity(Order order){
        OrderEntity entity = new OrderEntity(order.getId(), order.getStatus().name());

        for(var item : order.getItems()){
            entity.addItem(item.productId(), item.quantity());
        }

        return entity;
    }

    public static Order toDomain(OrderEntity entity) {
        List<OrderItem> items = entity.getItems().stream()
                .map(OrderMapper::toDomainItem)
                .toList();

        // Domain tarafında invariant var: Order.create(...) CREATED ile başlar.
        Order order = Order.create(entity.getId(), items);

        OrderStatus persistedStatus = OrderStatus.valueOf(entity.getStatus());
        applyPersistedStatus(order, persistedStatus);

        return order;
    }

    private static void applyPersistedStatus(Order order, OrderStatus status) {
        // Domain kurallarını “bypass” etme, status’u setter’la verme.
        // Mevcut davranış: state machine üzerinden taşı.
        switch (status) {
            case CREATED -> { /* nothing */ }

            case PAID -> order.pay();

            case PREPARING -> {
                order.pay();
                order.startPreparing();
            }

            case SHIPPED -> {
                order.pay();
                order.startPreparing();
                order.ship();
            }

            case DELIVERED -> {
                order.pay();
                order.startPreparing();
                order.ship();
                order.deliver();
            }

            case CANCELLED -> {
                // Cancel CREATED veya PAID’den olur. DB’de CANCELLED gördüysek,
                // en deterministik yol: CREATED’den direkt cancel.
                order.cancel();
            }

            default -> throw new IllegalStateException("Unsupported status: " + status);
        }
    }
    private static OrderItem toDomainItem(OrderItemEntity itemEntity) {
        UUID productId = itemEntity.getId().getProductId();
        int quantity = itemEntity.getQuantity();
        return new OrderItem(productId, quantity);
    }

}
