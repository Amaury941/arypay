package com.arypay.RabbitMQtest;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Hello world!
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin"); // padrão do RabbitMQ
        factory.setPassword("admin"); // padrão do RabbitMQ
        
        try(Connection connection = factory.newConnection();Channel channel = connection.createChannel()) {
            channel.queueDeclare(
                QUEUE_NAME,
                true,
                false,
                false,
                Map.of("x-queue-type","quorum")
            );
            String message = "Rocambole";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] sent '"+message+"'");
        }
    }
}
