package com.niit.jap.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {


    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendMessage(OrderDTO orderDTO) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "userorderkey", orderDTO);
    }
}
