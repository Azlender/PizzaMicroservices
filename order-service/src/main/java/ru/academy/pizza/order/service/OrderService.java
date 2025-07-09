package ru.academy.pizza.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.academy.pizza.order.client.MenuClient;
import ru.academy.pizza.order.client.DishResponseDto;
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

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.user_name());
        order.setOrderDate(LocalDateTime.now()); //

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

        StatusEntity status = statusRepository.findByName("CREATED")
                .orElseThrow(() -> new RuntimeException("Status 'CREATED' not found"));

        OrderEntity entity = orderMapper.toEntity(order);
        entity.setStatus(status);

        entity.getItems().forEach(i -> i.setOrder(entity));

        OrderEntity saved = orderRepository.save(entity);

        return new CreateOrderResponse(saved.getId(), status.getName());
    }

    public OrderResponse getOrderById(Long id) {
        OrderEntity entity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        Order order = orderMapper.toModel(entity);
        return orderMapper.toResponse(order);
    }
}
