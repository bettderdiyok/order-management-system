package com.betul.oms.application.usecase.order.pay;

import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class PayOrderService implements PayOrderUseCase {
    private final OrderRepository orderRepository;

    public PayOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PayOrderResult execute(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.pay();
        orderRepository.save(order);
        return new PayOrderResult(
                order.getId(),
                order.getStatus()
        );
    }
}
