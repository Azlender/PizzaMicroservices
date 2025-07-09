// MenuServiceImpl.java
package ru.academy.pizza.menu.service;

import ru.academy.pizza.menu.dto.DishResponseDto;
import ru.academy.pizza.menu.dto.DishSummaryDto;
import ru.academy.pizza.menu.entity.DishEntity;
import ru.academy.pizza.menu.repository.DishRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final DishRepository dishRepository;

    public MenuService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DishSummaryDto> getAllDishes() {
        return dishRepository.findAllWithCategoryAndKitchen().stream()
                .map(this::convertToSummaryDto)
                .collect(Collectors.toList());
    }

    public DishResponseDto getDishById(Long id) {
        DishEntity dish = dishRepository.findByIdWithCategoryAndKitchen(id)
                .orElseThrow(() -> new DishNotFoundException(id));
        return convertToResponseDto(dish);
    }

    private DishSummaryDto convertToSummaryDto(DishEntity entity) {
        return new DishSummaryDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getName(),
                entity.getCategory().getKitchen().getName()
        );
    }

    private DishResponseDto convertToResponseDto(DishEntity entity) {
        return new DishResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getCategory().getName(),
                entity.getCategory().getKitchen().getName()
        );
    }

    // Дополнительный класс для обработки ошибок
    private static class DishNotFoundException extends RuntimeException {
        public DishNotFoundException(Long id) {
            super("Dish not found with id: " + id);
        }
    }
}