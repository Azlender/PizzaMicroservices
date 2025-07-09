package ru.academy.pizza.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.pizza.order.entity.OrderItemEntity;

public interface OrderItemsRepository extends JpaRepository<OrderItemEntity, Integer> {
}

