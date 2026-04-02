package com.sekazedy.enterpriseordermanagementsystem.listener;

import com.sekazedy.enterpriseordermanagementsystem.event.OrderCancelledEvent;
import com.sekazedy.enterpriseordermanagementsystem.event.OrderDeliveredEvent;
import com.sekazedy.enterpriseordermanagementsystem.event.OrderPaidEvent;
import com.sekazedy.enterpriseordermanagementsystem.event.OrderShippedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    @Async
    @EventListener
    public void handleOrderPaid(OrderPaidEvent event) {
        System.out.println("Notify user: Order " + event.orderId() + " has been PAID");
    }

    @Async
    @EventListener
    public void handleOrderShipped(OrderShippedEvent event) {
        System.out.println("Notify user: Order " + event.orderId() + " has been SHIPPED");
    }

    @Async
    @EventListener
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        System.out.println("Notify user: Order " + event.orderId() + " has been DELIVERED");
    }

    @Async
    @EventListener
    public void handleOrderCancelled(OrderCancelledEvent event) {
        System.out.println("Notify user: Order " + event.orderId() + " has been CANCELLED");
    }
}
