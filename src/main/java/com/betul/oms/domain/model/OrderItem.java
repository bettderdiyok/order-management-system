package com.betul.oms.domain.model;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity){
    public OrderItem {
        if(productId == null)
            throw new IllegalArgumentException();
        if(quantity <= 0)
            throw new IllegalArgumentException();
    }
}
