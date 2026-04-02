package com.sekazedy.enterpriseordermanagementsystem.controller;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateOrderRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderResponse;
import com.sekazedy.enterpriseordermanagementsystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @PostMapping("/{id}/pay")
    public OrderResponse pay(@PathVariable Long id) {
        return orderService.payOrder(id);
    }
}
