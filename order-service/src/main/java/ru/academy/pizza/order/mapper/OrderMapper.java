package ru.academy.pizza.order.mapper;


import ru.academy.pizza.order.dto.OrderItemResponse;
import ru.academy.pizza.order.dto.OrderResponse;
import ru.academy.pizza.order.entity.*;
import ru.academy.pizza.order.model.Order;
import ru.academy.pizza.order.model.OrderItem;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerName(order.getCustomerName());
        entity.setOrderDate(order.getOrderDate());

        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(this::toItemEntity)
                .collect(Collectors.toList());

        entity.setItems(itemEntities);
        return entity;
    }

    private OrderItemEntity toItemEntity(OrderItem item) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setDishId(item.getDishId());
        entity.setDishName(item.getDishName());
        entity.setQuantity(item.getQuantity());
        entity.setPrice(item.getPrice());
        return entity;
    }

    public Order toModel(OrderEntity entity) {
        Order order = new Order();
        order.setId(entity.getId());
        order.setCustomerName(entity.getCustomerName());
        order.setOrderDate(entity.getOrderDate());
        order.setStatus(entity.getStatus().getName());

        List<OrderItem> items = entity.getItems().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    private OrderItem toOrderItem(OrderItemEntity entity) {
        return new OrderItem(
                entity.getDishId(),
                entity.getDishName(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getDishId(),
                        item.getDishName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getOrderDate(),
                order.getStatus(),
                items,
                order.getTotalPrice()
        );
    }
}