package com.betul.oms.application.usecase.order.create;

import java.util.List;

public record CreateOrderCommand(List<CreateOrderItemCommand> items) {} //Usecase input
