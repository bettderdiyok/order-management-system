package com.betul.oms.application.usecase;

import com.betul.oms.domain.model.Order;

public interface CreateOrderUseCase {

 Order create(CreateOrderCommand command);

}
