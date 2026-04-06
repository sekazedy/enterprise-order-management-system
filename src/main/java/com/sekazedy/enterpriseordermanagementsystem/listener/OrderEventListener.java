package com.sekazedy.enterpriseordermanagementsystem.listener;

import com.sekazedy.enterpriseordermanagementsystem.config.RabbitConfig;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    @RabbitListener(queues = RabbitConfig.DELIVERED_QUEUE)
    public void handleDelivered(OrderEvent event) {
        System.out.println("Order delivered: " + event.getOrderId());
    }

    @RabbitListener(queues = RabbitConfig.SHIPPED_QUEUE)
    public void handleShipped(OrderEvent event) {
        System.out.println("Order shipped: " + event.getOrderId());
    }

    @RabbitListener(queues = RabbitConfig.PAID_QUEUE)
    public void handlePaid(OrderEvent event) {
        System.out.println("Order paid: " + event.getOrderId());
    }

    @RabbitListener(queues = RabbitConfig.CANCELLED_QUEUE)
    public void handleCancelled(OrderEvent event) {
        System.out.println("Order cancelled: " + event.getOrderId());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
