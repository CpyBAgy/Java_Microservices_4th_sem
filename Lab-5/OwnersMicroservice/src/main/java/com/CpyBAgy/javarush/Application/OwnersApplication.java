package com.CpyBAgy.javarush.Application;

import com.CpyBAgy.javarush.RabbitMQConfiguration.OwnersRabbitMQConfiguration;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OwnersRabbitMQConfiguration.class)
public class OwnersApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OwnersApplication.class, args);
        CachingConnectionFactory connectionFactory = context.getBean(CachingConnectionFactory.class);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        System.out.println("RabbitMQ connection status: " + rabbitAdmin.getQueueProperties("ownersCreateQueue"));
    }
}

