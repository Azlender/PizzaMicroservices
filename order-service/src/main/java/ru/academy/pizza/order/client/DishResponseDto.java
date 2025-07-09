package ru.academy.pizza.order.client;

import java.math.BigDecimal;

public record DishResponseDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String category,
        String kitchen
) {}
