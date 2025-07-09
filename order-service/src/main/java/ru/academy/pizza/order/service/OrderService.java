package ru.academy.pizza.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.academy.pizza.order.client.KitchenClient;
import ru.academy.pizza.order.client.MenuClient;
import ru.academy.pizza.order.dto.DishResponseDto;
import ru.academy.pizza.order.dto.CreateOrderRequest;
import ru.academy.pizza.order.dto.CreateOrderResponse;
import ru.academy.pizza.order.dto.OrderResponse;
import ru.academy.pizza.order.entity.OrderEntity;
import ru.academy.pizza.order.entity.StatusEntity;
import ru.academy.pizza.order.exceptions.OrderNotFoundException;
import ru.academy.pizza.order.mapper.OrderMapper;
import ru.academy.pizza.order.model.Order;
import ru.academy.pizza.order.model.OrderItem;
import ru.academy.pizza.order.repository.OrderRepository;
import ru.academy.pizza.order.repository.StatusRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;
    private final OrderMapper orderMapper;
    private final MenuClient menuClient;
    private final KitchenClient kitchenClient;

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        OrderEntity savedOrder = saveOrderWithStatus(request);
        kitchenClient.sendToKitchen(savedOrder.getId());
        return new CreateOrderResponse(savedOrder.getId(), savedOrder.getStatus().getName());
    }


    @Transactional
    protected OrderEntity saveOrderWithStatus(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.user_name());
        order.setOrderDate(LocalDateTime.now());

        request.items().forEach(itemRequest -> {
            DishResponseDto dish = menuClient.getDishById(itemRequest.itemId());

            OrderItem item = new OrderItem(
                    dish.id(),
                    dish.name(),
                    itemRequest.qty(),
                    dish.price()
            );

            order.addItem(item);
        });

        StatusEntity statusCreated = statusRepository.findByName("CREATED")
                .orElseThrow(() -> new RuntimeException("Status 'CREATED' not found"));

        OrderEntity entity = orderMapper.toEntity(order);
        entity.setStatus(statusCreated);

        entity.getItems().forEach(i -> i.setOrder(entity));

        OrderEntity saved = orderRepository.save(entity);

        StatusEntity statusProcessing = statusRepository.findByName("PROCESSING")
                .orElseThrow(() -> new RuntimeException("Status 'PROCESSING' not found"));

        saved.setStatus(statusProcessing);
        return orderRepository.save(saved);
    }


    public OrderResponse getOrderById(Long id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        Order order = orderMapper.toModel(entity);
        return orderMapper.toResponse(order);
    }

    @Transactional
    public void updateOrderStatus(Long id, String statusName) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        StatusEntity status = statusRepository.findByName(statusName)
                .orElseThrow(() -> new RuntimeException("Status '" + statusName + "' not found"));

        order.setStatus(status);
        orderRepository.save(order);
    }
}
