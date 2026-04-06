package com.sekazedy.enterpriseordermanagementsystem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "order.exchange";
    public static final String PAID_QUEUE = "order.paid.queue";
    public static final String SHIPPED_QUEUE = "order.shipped.queue";
    public static final String DELIVERED_QUEUE = "order.delivered.queue";
    public static final String CANCELLED_QUEUE = "order.cancelled.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue paidQueue() {
        return new Queue(PAID_QUEUE);
    }

    @Bean
    public Queue shippedQueue() {
        return new Queue(SHIPPED_QUEUE);
    }

    @Bean
    public Queue deliveredQueue() {
        return new Queue(DELIVERED_QUEUE);
    }

    @Bean
    public Queue cancelledQueue() {
        return new Queue(CANCELLED_QUEUE);
    }

    @Bean
    public Binding paidBinding() {
        return BindingBuilder
                .bind(paidQueue())
                .to(exchange())
                .with("order.paid");
    }

    @Bean
    public Binding shippedBinding() {
        return BindingBuilder
                .bind(shippedQueue())
                .to(exchange())
                .with("order.shipped");
    }

    @Bean
    public Binding deliveredBinding() {
        return BindingBuilder
                .bind(deliveredQueue())
                .to(exchange())
                .with("order.delivered");
    }

    @Bean
    public Binding cancelledBinding() {
        return BindingBuilder
                .bind(cancelledQueue())
                .to(exchange())
                .with("order.cancelled");
    }
}
