package com.betul.oms.domain.model;

import com.betul.oms.domain.exception.ValidationException;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity){
    public OrderItem {
        if(productId == null)
            throw new ValidationException("productId", "must not be null");
        if(quantity <= 0)
            throw new ValidationException("quantity", "must be greater than zero");
    }
}
