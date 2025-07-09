package ru.academy.pizza.order.dto;

public record OrderItemRequest(
        Long itemId, // dishId из menu-service
        Integer qty   // quantity
) {}
