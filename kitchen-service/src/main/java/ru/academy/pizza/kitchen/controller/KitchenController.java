package ru.academy.pizza.kitchen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.academy.pizza.kitchen.dto.KitchenOrderRequest;
import ru.academy.pizza.kitchen.dto.KitchenOrderResponse;
import ru.academy.pizza.kitchen.service.KitchenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kitchen")
public class KitchenController {

    private final KitchenService kitchenService;

    @PostMapping("/cook")
    public ResponseEntity<KitchenOrderResponse> startCooking(@RequestBody KitchenOrderRequest request) {
        kitchenService.startCooking(request.orderId());
        return ResponseEntity.ok(new KitchenOrderResponse(request.orderId(), "cooking started"));
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<KitchenOrderResponse> getStatus(@PathVariable Long orderId) {
        String status = kitchenService.getStatus(orderId);
        return ResponseEntity.ok(new KitchenOrderResponse(orderId, status));
    }
}
