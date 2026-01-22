package com.betul.oms.application.usecase;

import com.betul.oms.application.usecase.order.create.CreateOrderCommand;
import com.betul.oms.application.usecase.order.create.CreateOrderItemCommand;
import com.betul.oms.application.usecase.order.create.CreateOrderUseCase;
import com.betul.oms.application.usecase.order.create.CreateOrderService;
import com.betul.oms.application.usecase.order.create.CreateOrderResult;
import com.betul.oms.domain.exception.ValidationException;
import com.betul.oms.domain.repository.OrderRepository;
import com.betul.oms.infrastructure.persistence.inmemory.InMemoryOrderRepository;
import com.betul.oms.domain.model.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CreateOrderUseCaseTest {
    @Test
    void should_throw_validation_when_command_is_null() {
        OrderRepository repo = new InMemoryOrderRepository();
        CreateOrderUseCase useCase = new CreateOrderService(repo);

        assertThrows(ValidationException.class, () -> useCase.create(null));
    }

    @Test
    void should_throw_validation_when_productId_is_null() {
        OrderRepository orderRepository = new InMemoryOrderRepository();
        CreateOrderUseCase useCase = new CreateOrderService(orderRepository);
        assertThrows(ValidationException.class,
                () -> useCase.create(new CreateOrderCommand(List.of(new CreateOrderItemCommand(null, 2)))));
    }


    @Test
    void should_throw_validation_when_items_is_null() {
        OrderRepository orderRepository = new InMemoryOrderRepository();
        CreateOrderUseCase useCase = new CreateOrderService(orderRepository);
        assertThrows(ValidationException.class,
                () -> useCase.create(new CreateOrderCommand(null)));
    }


    @Test
    void should_throw_validation_when_items_is_empty() {
        OrderRepository orderRepository = new InMemoryOrderRepository();
        CreateOrderUseCase useCase = new CreateOrderService(orderRepository);
        assertThrows(
                ValidationException.class,
                () -> useCase.create(new CreateOrderCommand(List.of()))
        );
    }

    @Test
    void should_persist_order_and_return_result(){
        OrderRepository repository = new InMemoryOrderRepository();
        CreateOrderUseCase createOrderUseCase = new CreateOrderService(repository);

        UUID productId = UUID.randomUUID();
        CreateOrderCommand command = new CreateOrderCommand(List.of(new CreateOrderItemCommand(productId,2)));

        CreateOrderResult result = createOrderUseCase.create(command);

        assertNotNull(result);
        assertNotNull(result.orderId());
        assertNotNull(result.status());

        Order saved = repository.findById(result.orderId()).orElseThrow(() -> new AssertionError("Order was not persisted."));

        assertEquals(result.orderId(), saved.getId());
        assertEquals(result.status(), saved.getStatus());

        assertEquals(1, saved.getItems().size());
        assertEquals(productId, saved.getItems().get(0).productId());
        assertEquals(2, saved.getItems().get(0).quantity());


    }

    @Test
    void should_throw_validation_when_quantity_is_zero(){
        OrderRepository repository = new InMemoryOrderRepository();
        CreateOrderUseCase createOrderUseCase = new CreateOrderService(repository);

        UUID productId = UUID.randomUUID();

        assertThrows(
                ValidationException.class,
                () -> createOrderUseCase.create(new CreateOrderCommand(
                        List.of(new CreateOrderItemCommand(productId, 0))))
        );

    }
}
