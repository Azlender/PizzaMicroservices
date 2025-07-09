package ru.academy.pizza.kitchen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.academy.pizza.kitchen.dto.StatusUpdateDto;
import ru.academy.pizza.kitchen.entity.KitchenOrderEntity;
import ru.academy.pizza.kitchen.repository.KitchenOrderRepository;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenOrderRepository kitchenOrderRepository;
    private final RestTemplate restTemplate;

    @Value("${kitchen.max-concurrent-orders}")
    private int maxConcurrentOrders;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    @Transactional
    public void startCooking(Long orderId) {
        if (kitchenOrderRepository.countByStatus("COOKING") >= maxConcurrentOrders) {
            throw new IllegalStateException("Максимальное количество заказов в готовке достигнуто");
        }

        KitchenOrderEntity order = new KitchenOrderEntity();
        order.setOrderId(orderId);
        order.setStatus("COOKING");
        kitchenOrderRepository.save(order);

        // Запускаем процесс готовки асинхронно, чтобы не блокировать поток
        cookOrderAsync(order);
    }

    //TODO: сделать асинхронным
    public void cookOrderAsync(KitchenOrderEntity order) {
        try {
            Thread.sleep(300);  // имитация готовки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Обновляем статус на DONE
        updateStatusDone(order.getId());

        // Отправляем статус в внешний сервис (используем внешний orderId)
        StatusUpdateDto statusUpdate = new StatusUpdateDto("COMPLETED");
        String url = orderServiceUrl;
        url += + order.getOrderId() + "/status";
        System.out.println(url);
        restTemplate.postForEntity(url, statusUpdate, Void.class);
    }

    @Transactional
    public void updateStatusDone(Long kitchenOrderId) {
        kitchenOrderRepository.findById(kitchenOrderId).ifPresent(order -> {
            order.setStatus("DONE");
        });
    }

    public String getStatus(Long orderId) {
        return kitchenOrderRepository.findByOrderId(orderId)
                .map(KitchenOrderEntity::getStatus)
                .orElse("ORDER NOT FOUND");
    }
}

