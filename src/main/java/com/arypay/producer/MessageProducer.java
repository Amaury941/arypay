package com.arypay.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;
    
    @Autowired
    public MessageProducer(RabbitTemplate template) {
        this.rabbitTemplate = template;
    }

    public void SendMessage(String exchange, String key, Object mensagem) {
        rabbitTemplate.convertAndSend(exchange, key, mensagem);
    }
}