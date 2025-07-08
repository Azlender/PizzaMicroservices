package ru.academy.pizza.menu.controller;

import ru.academy.pizza.menu.dto.DishResponseDto;
import ru.academy.pizza.menu.dto.DishSummaryDto;
import ru.academy.pizza.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<DishSummaryDto>> getAllDishes() {
        return ResponseEntity.ok(menuService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuService.getDishById(id));
    }
}
