package com.betul.oms.domain.exception.order;

import com.betul.oms.domain.model.OrderStatus;
import com.betul.oms.domain.exception.BusinessRuleViolationException;

import java.util.List;

public class InvalidOrderStatusTransitionException extends BusinessRuleViolationException {
    public InvalidOrderStatusTransitionException(String action, OrderStatus current, OrderStatus expected) {
        super("Order cannot " + action + " when status is " + current + "; expected " + expected);
    }

    public InvalidOrderStatusTransitionException(String action, OrderStatus current, List<OrderStatus> expected) {
        super("Order cannot " + action + " when status is " + current + ". Expected one of: " + expected);
    }
}
