package ru.academy.pizza.kitchen.mapper;

import org.springframework.stereotype.Component;
import ru.academy.pizza.kitchen.model.KitchenOrder;
import ru.academy.pizza.kitchen.dto.KitchenOrderResponse;
import ru.academy.pizza.kitchen.entity.KitchenOrderEntity;

@Component
public class KitchenOrderMapper {

    public KitchenOrderEntity toEntity(Long orderId, String status) {
        KitchenOrderEntity entity = new KitchenOrderEntity();
        entity.setOrderId(orderId);
        entity.setStatus(status);
        return entity;
    }

    public KitchenOrderResponse toDto(KitchenOrderEntity entity) {
        return new KitchenOrderResponse(entity.getOrderId(), entity.getStatus());
    }
}

