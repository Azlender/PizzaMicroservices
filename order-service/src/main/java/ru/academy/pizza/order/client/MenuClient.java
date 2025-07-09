package ru.academy.pizza.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.academy.pizza.order.client.DishResponseDto;

@Component
@RequiredArgsConstructor
public class MenuClient {

    private final RestTemplate restTemplate;

    @Value("${menu.service.url}")
    private String menuServiceUrl;

    public DishResponseDto getDishById(Long id) {
        return restTemplate.getForObject(menuServiceUrl + "/" + id, DishResponseDto.class);
    }
}
