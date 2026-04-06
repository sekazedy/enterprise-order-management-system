package com.sekazedy.enterpriseordermanagementsystem.service;

import com.sekazedy.enterpriseordermanagementsystem.config.RabbitConfig;
import com.sekazedy.enterpriseordermanagementsystem.dto.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderPaid(OrderEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                "order.paid",
                event
        );
    }

    public void publishOrderShipped(OrderEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                "order.shipped",
                event
        );
    }

    public void publishOrderDelivered(OrderEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                "order.delivered",
                event
        );
    }

    public void publishOrderCancelled(OrderEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                "order.cancelled",
                event
        );
    }
}
