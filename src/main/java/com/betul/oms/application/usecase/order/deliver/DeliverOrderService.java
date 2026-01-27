package com.betul.oms.application.usecase.order.deliver;

import com.betul.oms.application.usecase.order.common.OrderActionResult;
import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliverOrderService implements DeliverOrderUseCase {
    private final OrderRepository orderRepository;

    public DeliverOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderActionResult execute(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order Not Found"));
        order.deliver();
        orderRepository.save(order);
        return new OrderActionResult(
                order.getId(),
                order.getStatus()
        );
    }
}
