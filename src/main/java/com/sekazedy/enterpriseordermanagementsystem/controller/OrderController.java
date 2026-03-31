package com.sekazedy.enterpriseordermanagementsystem.controller;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateOrderRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderResponse;
import com.sekazedy.enterpriseordermanagementsystem.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}
