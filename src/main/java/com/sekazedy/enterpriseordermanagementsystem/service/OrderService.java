package com.sekazedy.enterpriseordermanagementsystem.service;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateOrderRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderEvent;
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
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        OrderEventPublisher orderEventPublisher) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.eventPublisher = orderEventPublisher;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Order order = new Order(user, product, OrderStatus.NEW);
        orderRepository.save(order);

        return toResponse(order);
    }

    @Transactional
    public OrderResponse payOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);

        if (order.getOrderStatus() != OrderStatus.NEW) {
            throw new InvalidOrderStateException("Order cannot be paid in current state");
        }

        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);

        eventPublisher.publishOrderPaid(
            new OrderEvent(
                order.getId(),
                order.getUser().getUserName(),
                order.getProduct().getName()
            )
        );

        return toResponse(order);
    }

    @Transactional
    public OrderResponse shipOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);

        if (order.getOrderStatus() != OrderStatus.PAID) {
            throw new InvalidOrderStateException("Only PAID orders can be shipped");
        }

        order.setOrderStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);

        eventPublisher.publishOrderShipped(
            new OrderEvent(
                order.getId(),
                order.getUser().getUserName(),
                order.getProduct().getName()
            )
        );

        return toResponse(order);
    }

    @Transactional
    public OrderResponse deliverOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);

        if (order.getOrderStatus() != OrderStatus.SHIPPED) {
            throw new InvalidOrderStateException("Only SHIPPED orders can be delivered");
        }

        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);

        eventPublisher.publishOrderDelivered(
            new OrderEvent(
                order.getId(),
                order.getUser().getUserName(),
                order.getProduct().getName()
            )
        );

        return toResponse(order);
    }

    @Transactional
    public OrderResponse cancelOrder(Long orderId) {
        Order order = getOrderOrThrow(orderId);

        if (order.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderStateException("DELIVERED orders cannot be cancelled");
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        eventPublisher.publishOrderCancelled(
            new OrderEvent(
                order.getId(),
                order.getUser().getUserName(),
                order.getProduct().getName()
            )
        );

        return toResponse(order);
    }

    private @NonNull Order getOrderOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
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
