package com.betul.oms.application.usecase.order.create;

import com.betul.oms.application.usecase.order.common.OrderActionResult;
import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.model.OrderItem;
import com.betul.oms.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderService implements CreateOrderUseCase {
    private final OrderRepository orderRepository;

    public CreateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderActionResult execute(CreateOrderCommand command) {
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

        return new OrderActionResult(
                order.getId(),
                order.getStatus()
        );
    }
}
