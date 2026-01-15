package com.betul.oms.application.usecase;

import com.betul.oms.domain.exception.NotFoundException;
import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GetOrderUseCaseImpl implements GetOrderUseCase {
    private final OrderRepository orderRepository;

    public GetOrderUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public GetOrderResult getById(UUID id) {
        if (id == null) {
            throw new ValidationException("id", "id is null");
        }

        Order order =  orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return new GetOrderResult(
                order.getId(),
                order.getStatus().name(),
                order.getItems().stream().toList()
        );
    }
}
