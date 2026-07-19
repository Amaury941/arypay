package com.arypay.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("broker.queue.ticket")
@Component
@Getter
@Setter
public class TicketQueueProperties {
    public String name;
}