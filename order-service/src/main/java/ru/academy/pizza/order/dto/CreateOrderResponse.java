package ru.academy.pizza.order.dto;

public record CreateOrderResponse(
        Long orderId,
        String status
) {}
