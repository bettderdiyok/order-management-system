package com.betul.oms.api.mapper;

import com.betul.oms.api.response.GetOrderResponse;
import com.betul.oms.application.usecase.order.get.GetOrderResult;

public class GetOrderApiMapper {

    public static GetOrderResponse toGetOrderResponse(GetOrderResult result) {
        return new GetOrderResponse(
                result.orderId(),
                result.status(),
                result.orderItem()
        );

    }
}
