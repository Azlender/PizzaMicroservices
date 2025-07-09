package ru.academy.pizza.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        String customerName,
        LocalDateTime orderDate,
        String status,
        List<OrderItemResponse> items,
        BigDecimal totalPrice
) {}