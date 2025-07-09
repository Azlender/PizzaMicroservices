package ru.academy.pizza.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.pizza.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

