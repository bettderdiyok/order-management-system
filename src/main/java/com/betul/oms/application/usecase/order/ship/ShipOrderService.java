package com.betul.oms.application.usecase.order.ship;

import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ShipOrderService implements ShipOrderUseCase{
    private final OrderRepository orderRepository;

    public ShipOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public ShipOrderResult execute(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        order.ship();
        orderRepository.save(order);
        return new ShipOrderResult(
                order.getId(),
                order.getStatus()
       );
    }
}
