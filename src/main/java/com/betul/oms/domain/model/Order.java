package com.betul.oms.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    @Getter
    private final UUID id;
    private final List<OrderItem> items;
    @Getter
    private OrderStatus status = OrderStatus.CREATED;

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public static Order create(List<OrderItem> items){
        return create(UUID.randomUUID(), items);
    }

    public static Order create(UUID id, List<OrderItem> items) {
        if(id == null)
            throw new IllegalArgumentException("id cannot be null");
        if (items == null || items.isEmpty())
            throw new IllegalArgumentException("items cannot be empty");
        return new Order(id, List.copyOf(items));
    }
}
