package ru.academy.pizza.order.controller;

import ru.academy.pizza.order.dto.CreateOrderRequest;
import ru.academy.pizza.order.dto.CreateOrderResponse;
import ru.academy.pizza.order.dto.OrderResponse;
import ru.academy.pizza.order.dto.StatusUpdateDto;
import ru.academy.pizza.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDto statusDto) {
        orderService.updateOrderStatus(id, statusDto.status());
    }
}
