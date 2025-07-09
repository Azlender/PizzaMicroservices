package ru.academy.pizza.menu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "kitchen", schema = "menu_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KitchenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    private Short id;

    @Column(nullable = false, length = 30)
    private String name;
}
