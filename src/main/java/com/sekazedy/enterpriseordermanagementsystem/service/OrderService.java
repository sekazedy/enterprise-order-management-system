package com.sekazedy.enterpriseordermanagementsystem.service;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateOrderRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderResponse;
import com.sekazedy.enterpriseordermanagementsystem.exception.InvalidOrderStateException;
import com.sekazedy.enterpriseordermanagementsystem.exception.NotFoundException;
import com.sekazedy.enterpriseordermanagementsystem.model.Order;
import com.sekazedy.enterpriseordermanagementsystem.model.OrderStatus;
import com.sekazedy.enterpriseordermanagementsystem.model.Product;
import com.sekazedy.enterpriseordermanagementsystem.model.User;
import com.sekazedy.enterpriseordermanagementsystem.repository.OrderRepository;
import com.sekazedy.enterpriseordermanagementsystem.repository.ProductRepository;
import com.sekazedy.enterpriseordermanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Order order = new Order(user, product, OrderStatus.NEW);

        orderRepository.save(order);

        return new OrderResponse(
                order.getId(),
                user.getUserName(),
                product.getName(),
                order.getOrderStatus().name()
        );
    }

    @Transactional
    public OrderResponse payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Order cannot be paid in current state");
        }

        order.setOrderStatus(OrderStatus.PAID);

        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUser().getUserName(),
                order.getProduct().getName(),
                order.getOrderStatus().name()
        );
    }
}
