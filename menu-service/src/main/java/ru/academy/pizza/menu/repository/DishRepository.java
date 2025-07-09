package ru.academy.pizza.menu.repository;

import ru.academy.pizza.menu.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {

    // Получение всех блюд с категориями и кухнями
    @Query("SELECT d FROM DishEntity d " +
            "JOIN FETCH d.category c " +
            "JOIN FETCH c.kitchen")
    List<DishEntity> findAllWithCategoryAndKitchen();

    // Получение конкретного блюда с категорией и кухней
    @Query("SELECT d FROM DishEntity d " +
            "JOIN FETCH d.category c " +
            "JOIN FETCH c.kitchen " +
            "WHERE d.id = :id")
    Optional<DishEntity> findByIdWithCategoryAndKitchen(Long id);
}
