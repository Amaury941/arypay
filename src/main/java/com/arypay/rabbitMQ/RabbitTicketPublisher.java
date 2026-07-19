package com.arypay.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.arypay.rabbitMQ.config.TicketQueueProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RabbitTicketPublisher{
    private final TicketQueueProperties ticketQueueProperties;
    private final RabbitTemplate rabbitTemplate;

    public void publish(String text) {
        log.info("notifying queue{} of text {}",ticketQueueProperties.getName(),text);
        rabbitTemplate.convertAndSend(ticketQueueProperties.getName(),text);
    }
}
