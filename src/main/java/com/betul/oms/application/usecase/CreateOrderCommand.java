package com.betul.oms.application.usecase;

import java.util.List;

public record CreateOrderCommand(List<CreateOrderItemCommand> items) {} //Usecase input
