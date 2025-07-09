package ru.academy.pizza.kitchen.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitchen_orders", schema = "kitchen_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KitchenOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String status;
}
