package ru.academy.pizza.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenRequest {
    private Long orderId;

    public KitchenRequest() {}
    public KitchenRequest(Long orderId) {
        this.orderId = orderId;
    }

}
