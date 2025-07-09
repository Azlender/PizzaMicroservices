package ru.academy.pizza.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status", schema = "order_service")
@Getter
@Setter
public class StatusEntity {
    @Id
    @Column(name = "status_id")
    private Short id;

    @Column(name = "status_name", nullable = false, length = 20)
    private String name;
}
