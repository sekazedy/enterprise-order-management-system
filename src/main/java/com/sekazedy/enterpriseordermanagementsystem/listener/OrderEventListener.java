package com.sekazedy.enterpriseordermanagementsystem.listener;

import com.sekazedy.enterpriseordermanagementsystem.event.OrderPaidEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    @Async
    @EventListener
    public void handleOrderPaid(OrderPaidEvent event) {
        System.out.println("Sending notification:");
        System.out.println("User: " + event.getUserName());
        System.out.println("Product: " + event.getProductName());
        System.out.println("Order ID: " + event.getOrderId());
    }
}
