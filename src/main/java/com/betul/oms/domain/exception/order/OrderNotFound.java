package com.betul.oms.domain.exception.order;

import com.betul.oms.domain.exception.NotFoundException;

public class OrderNotFound extends NotFoundException {
    public OrderNotFound(String message) {
        super(message);
    }
}
