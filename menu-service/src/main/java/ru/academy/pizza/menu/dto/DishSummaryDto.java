package ru.academy.pizza.menu.dto;

import java.math.BigDecimal;

public record DishSummaryDto(
        Long id,
        String name,
        BigDecimal price,
        String category,      // Название категории
        String kitchen        // Название кухни
) {}
