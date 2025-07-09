package ru.academy.pizza.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.academy.pizza.order.entity.StatusEntity;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Short> {
    Optional<StatusEntity> findByName(String name);
}
