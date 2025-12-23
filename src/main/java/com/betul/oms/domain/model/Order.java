package com.betul.oms.domain.model;

import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.exception.order.InvalidOrderStatusTransitionException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order { //Aggregate root
    @Getter
    private final UUID id;
    private final List<OrderItem> items;
    @Getter
    private OrderStatus status;

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public static Order create(List<OrderItem> items){
        return create(UUID.randomUUID(), items);
    }

    public static Order create(UUID id, List<OrderItem> items) { //Factory method
        if(id == null)
            throw new ValidationException("id", "must not be null");

        if(items == null)
            throw new ValidationException("items", "must not be null");

        if (items.isEmpty())
            throw new ValidationException("items", "must not be empty");

        Order order = new Order(id, List.copyOf(items));
        order.status = OrderStatus.CREATED;
        return order;
    }

    public void pay() {
        requireStatus(OrderStatus.CREATED, "be paid");
        this.status = OrderStatus.PAID;
    }

    public void startPreparing() {
        requireStatus(OrderStatus.PAID, "start preparing");
        this.status = OrderStatus.PREPARING;
    }

    public void ship() {
        requireStatus(OrderStatus.PREPARING, "be shipped");
        this.status = OrderStatus.SHIPPED;
    }

    public void deliver() {
        requireStatus(OrderStatus.SHIPPED, "be delivered");
        this.status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        requireStatus(List.of(OrderStatus.CREATED, OrderStatus.PAID), "be cancelled");
        this.status = OrderStatus.CANCELLED;
    }

    private void requireStatus(OrderStatus expected, String action) {
        if(this.status != expected)
            throw new InvalidOrderStatusTransitionException(
                    action,
                    this.status,
                    expected
            );
    }

    private void requireStatus(List<OrderStatus> allowedList, String action) {
        if(!allowedList.contains(this.status))
            throw new InvalidOrderStatusTransitionException(
                    action,
                    this.status,
                    allowedList
            );
    }
}
