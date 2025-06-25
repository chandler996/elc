package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.OrderRequest;
import org.example.backend.dto.OrderResponse;
import org.example.backend.entity.Order;
import org.example.backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public Order getOrderDetails(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}
