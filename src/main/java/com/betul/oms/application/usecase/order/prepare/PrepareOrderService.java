package com.betul.oms.application.usecase.order.prepare;

import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class PrepareOrderService implements PrepareOrderUseCase {
    private final OrderRepository orderRepository;

    public PrepareOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PrepareOrderResult execute(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        order.startPreparing();
        orderRepository.save(order);
        return new PrepareOrderResult(
                order.getId(),
                order.getStatus()
        );
    }
}
