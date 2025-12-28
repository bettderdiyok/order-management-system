package com.betul.oms.application.usecase;

import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.model.OrderItem;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CreateOrderResult create(CreateOrderCommand command) {
        if (command == null) {
            throw new ValidationException("command", "must not be null");
        }

        if (command.items() == null) {
            throw new ValidationException("items", "must not be null");
        }

        if (command.items().isEmpty()) {
            throw new ValidationException("items", "must not be empty");
        }
        List<OrderItem> domainItems = command.items().stream()
                .map(i -> new OrderItem(i.productId(), i.quantity())).toList();
        Order order = Order.create(domainItems);

        orderRepository.save(order);

        return new CreateOrderResult(
                order.getId(),
                order.getStatus()
        );
    }
}
