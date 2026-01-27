package com.betul.oms.application.usecase.order.create;

public interface CreateOrderUseCase {

  CreateOrderResult execute(CreateOrderCommand command);

}
