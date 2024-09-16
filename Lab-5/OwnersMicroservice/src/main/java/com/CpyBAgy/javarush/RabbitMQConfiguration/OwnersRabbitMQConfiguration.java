package com.CpyBAgy.javarush.RabbitMQConfiguration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnersRabbitMQConfiguration {

    @Bean
    public DirectExchange ownersDirectExchange() {
        return new DirectExchange("owners-exchange");
    }

    @Bean
    public Queue ownersGetByIdQueue() {
        return new Queue("ownersGetByIdQueue");
    }

    @Bean
    public Queue ownersGetWithFilterQueue() {
        return new Queue("ownersGetWithFilterQueue");
    }

    @Bean
    public Queue ownersCreateQueue() {
        return new Queue("ownersCreateQueue");
    }

    @Bean
    public Queue ownersUpdateQueue() {
        return new Queue("ownersUpdateQueue");
    }

    @Bean
    public Queue ownersRemoveQueue() {
        return new Queue("ownersRemoveQueue");
    }

    @Bean
    public Binding bindOwnersGetByIdQueue() {
        return BindingBuilder.bind(ownersGetByIdQueue()).to(ownersDirectExchange()).with("ownersGetByIdQueue");
    }

    @Bean
    public Binding bindOwnersGetWithFilterQueue() {
        return BindingBuilder.bind(ownersGetWithFilterQueue()).to(ownersDirectExchange()).with("ownersGetWithFilterQueue");
    }

    @Bean
    public Binding bindOwnersCreateQueue() {
        return BindingBuilder.bind(ownersCreateQueue()).to(ownersDirectExchange()).with("ownersCreateQueue");
    }

    @Bean
    public Binding bindOwnersUpdateQueue() {
        return BindingBuilder.bind(ownersUpdateQueue()).to(ownersDirectExchange()).with("ownersUpdateQueue");
    }


    @Bean
    public Binding bindOwnersRemoveQueue() {
        return BindingBuilder.bind(ownersRemoveQueue()).to(ownersDirectExchange()).with("ownersRemoveQueue");
    }
}

