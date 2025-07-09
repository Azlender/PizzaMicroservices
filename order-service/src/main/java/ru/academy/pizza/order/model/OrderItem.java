package ru.academy.pizza.order.model;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

//бизнес сущность блюда которое добавляется в заказ
@Getter
@Setter
public class OrderItem {
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Long dishId, String dishName, Integer quantity, BigDecimal price) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
