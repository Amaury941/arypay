package com.arypay.beans;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arypay.properties.TicketQueueProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConfiguration {
    private final TicketQueueProperties ticketQueueProperties;
    
    @Bean
    public Queue queue() {
        log.info("looking for queue {}", ticketQueueProperties.getName());
        return new Queue(ticketQueueProperties.getName(), true);
    }
}
