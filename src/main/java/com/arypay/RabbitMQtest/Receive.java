package com.arypay.RabbitMQtest;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public record Receive() {
    private final static String QUEUE_NAME = "hello";
    public static void main (String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin"); // padrão do RabbitMQ
        factory.setPassword("admin"); // padrão do RabbitMQ
 
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String, Object> args = Map.of("x-queue-type","quorum");
        channel.queueDeclare(QUEUE_NAME, true, false, false, args);
        System.out.println(" [*] Waiting for messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("[x] Received '"+message+"'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback,consumerTag->{});
    }
}
