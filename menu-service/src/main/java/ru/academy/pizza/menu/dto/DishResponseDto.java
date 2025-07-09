package ru.academy.pizza.menu.dto;


import java.math.BigDecimal;

//для детальной информации о блюде
public record DishResponseDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        String category,      // Название категории
        String kitchen        // Название кухни
) {}
