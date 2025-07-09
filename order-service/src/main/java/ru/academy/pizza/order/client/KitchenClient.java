package ru.academy.pizza.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.academy.pizza.order.dto.KitchenRequest;

@Component
@RequiredArgsConstructor
public class KitchenClient {

    private final RestTemplate restTemplate;

    @Value("${kitchen.service.url}")
    private String kitchenServiceUrl;

    public void sendToKitchen(Long orderId) {
        KitchenRequest request = new KitchenRequest(orderId);
        restTemplate.postForEntity(kitchenServiceUrl + "/cook", new HttpEntity<>(request), Void.class);
    }
}
