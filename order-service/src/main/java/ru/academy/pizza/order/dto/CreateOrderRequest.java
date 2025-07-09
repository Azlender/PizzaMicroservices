package ru.academy.pizza.order.dto;

import java.util.List;

public record CreateOrderRequest(
        String user_name,
        List<OrderItemRequest> items
) {}
