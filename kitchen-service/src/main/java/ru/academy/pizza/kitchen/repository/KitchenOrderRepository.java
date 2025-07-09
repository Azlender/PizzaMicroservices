package ru.academy.pizza.kitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.academy.pizza.kitchen.entity.KitchenOrderEntity;

import java.util.Optional;

public interface KitchenOrderRepository extends JpaRepository<KitchenOrderEntity, Long> {

    // Подсчёт количества заказов с конкретным статусом
    int countByStatus(String status);

    // Поиск заказа по orderId (поля в KitchenOrderEntity)
    Optional<KitchenOrderEntity> findByOrderId(Long orderId);
}

