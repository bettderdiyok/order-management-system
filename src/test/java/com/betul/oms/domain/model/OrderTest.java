package com.betul.oms.domain.model;

import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.exception.order.InvalidOrderStatusTransitionException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {
    @Test
    void create_should_set_status_created() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void create_throw_validation_exception_when_id_is_null() {
       //TODO: validation exception tests
    }

    @Test
    void create_throw_validation_exception_when_items_is_null() {
        //TODO: validation exception tests
    }

    @Test
    void create_throw_validation_exception_when_items_is_empty() {
        //TODO: validation exception tests
    }

    @Test
    void pay_should_move_order_from_created_to_paid() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void pay_should_throw_expected_when_order_is_not_created() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();

        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::pay
        );
    }

    @Test
    void startPreparing_should_move_order_from_paid_to_preparing() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();
        assertEquals(OrderStatus.PREPARING, order.getStatus());
    }

    @Test
    void startPreparing_throw_expected_when_order_is_not_paid() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));

        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::startPreparing
        );
    }

    @Test
    void ship_should_move_order_from_startPreparing_to_ship() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();
        order.ship();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());
    }

    @Test
    void ship_throw_expected_when_order_is_not_startPreparing() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::ship
        );
    }

    @Test
    void deliver_should_move_order_from_ship_to_deliver() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();
        order.ship();
        order.deliver();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void deliver_throw_expected_when_order_is_not_ship() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::deliver
        );
    }

    @Test
    void cancel_should_move_order_from_created_to_cancelled() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.cancel();
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void cancel_should_move_order_from_pay_to_cancelled() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void cancel_should_throw_when_order_is_preparing() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();

        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::cancel
        );
    }

    @Test
    void cancel_should_throw_when_order_is_shipped() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();
        order.ship();

        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::cancel
        );
    }

    @Test
    void cancel_should_throw_when_order_is_delivered() {
        Order order = Order.create(List.of(new OrderItem(UUID.randomUUID(), 1)));
        order.pay();
        order.startPreparing();
        order.ship();
        order.deliver();

        assertThrows(
                InvalidOrderStatusTransitionException.class,
                order::cancel
        );
    }
}
