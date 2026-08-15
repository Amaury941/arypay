package com.arypay.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    public static final String NOME_FILA = "transfer-queue";
    public static final String NOME_EXCHANGE = "transfer-exchange";
    public static final String ROUTING_KEY = "new";

    @Bean
    public Queue queue() {
        return new Queue(NOME_FILA, true); 
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public CommandLineRunner inicializarFilas(ConnectionFactory connectionFactory) {
        return args -> {
            RabbitAdmin admin = new RabbitAdmin(connectionFactory);
            
            // Declarando os objetos diretamente no RabbitMQ via comandos diretos
            admin.declareQueue(queue());
            admin.declareExchange(exchange());
            admin.declareBinding(binding());
            
            System.out.println("====== FILAS DO RABBITMQ DECLARADAS EXPLICITAMENTE NO DOCKER ======");
        };
    }
}
