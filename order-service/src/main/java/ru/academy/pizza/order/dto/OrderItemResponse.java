package ru.academy.pizza.order.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long dishId,
        String dishName,
        Integer quantity,
        BigDecimal price
) {}