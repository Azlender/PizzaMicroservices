package ru.academy.pizza.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.academy.pizza.order.entity.OrderEntity;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}

