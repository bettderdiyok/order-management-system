package com.betul.oms.api.mapper;

import com.betul.oms.api.request.CreateOrderRequest;
import com.betul.oms.api.response.CreateOrderResponse;
import com.betul.oms.application.usecase.order.create.CreateOrderCommand;
import com.betul.oms.application.usecase.order.create.CreateOrderItemCommand;
import com.betul.oms.application.usecase.order.create.CreateOrderResult;

import java.util.List;

public class CreateOrderApiMapper {
    //request -> command (api DTO -> application DTO)
   public static CreateOrderCommand toCreateOrderCommand(CreateOrderRequest createOrderRequest) {
       List<CreateOrderItemCommand> items = createOrderRequest.items()
               .stream()
               .map(i -> new CreateOrderItemCommand(
                       i.productId(),
                       i.quantity()
               )).toList();
       return new CreateOrderCommand(items);
   }

   public static CreateOrderResponse toCreateOrderResponse(CreateOrderResult createOrderResult) {
       return new CreateOrderResponse(
               createOrderResult.orderId(),
               createOrderResult.status().name()
       );

   }
}

