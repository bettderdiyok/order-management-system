package com.betul.oms.application.usecase.order.create;

import com.betul.oms.application.usecase.order.common.OrderActionResult;

public interface CreateOrderUseCase {

 OrderActionResult execute(CreateOrderCommand command);

}
