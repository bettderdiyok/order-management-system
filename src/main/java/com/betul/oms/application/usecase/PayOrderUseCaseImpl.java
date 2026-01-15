package com.betul.oms.application.usecase;

import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class PayOrderUseCaseImpl implements PayOrderUseCase {
    private final OrderRepository orderRepository;

    public PayOrderUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void execute(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.pay(); // domain rule burada patlar

        orderRepository.save(order);
    }
}
