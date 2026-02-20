package com.betul.oms.infrastructure.persistence.postgres.mapper;

import com.betul.oms.domain.model.Order;
import com.betul.oms.infrastructure.persistence.postgres.entity.OrderEntity;

public final class OrderMapper {

    private OrderMapper(){}

    public static OrderEntity toOrderEntity(Order order){
        OrderEntity entity = new OrderEntity(order.getId(), order.getStatus().name());

        for(var item : order.getItems()){
            entity.addItem(item.productId(), item.quantity());
        }

        return entity;
    }
}
