package ru.academy.pizza.order.model;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Бизнес сущность для создания заказа
@Getter
@Setter
public class Order {
    private Long id;
    private String customerName;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void addItem(OrderItem item) {
        items.add(item);
        totalPrice = totalPrice.add(item.getTotalPrice());
    }
}