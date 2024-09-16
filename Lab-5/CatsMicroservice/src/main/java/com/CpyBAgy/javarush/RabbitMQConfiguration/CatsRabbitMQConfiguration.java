package com.CpyBAgy.javarush.RabbitMQConfiguration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatsRabbitMQConfiguration {
    @Bean
    public DirectExchange catsDirectExchange() {
        return new DirectExchange("cats-exchange");
    }

    @Bean
    public Queue catsGetByIdQueue() {
        return new Queue("catsGetByIdQueue");
    }

    @Bean
    public Queue catsCreateQueue() {
        return new Queue("catsCreateQueue");
    }

    @Bean
    public Queue catsUpdateQueue() {
        return new Queue("catsUpdateQueue");
    }

    @Bean
    public Queue catsUpdateNameQueue() {
        return new Queue("catsUpdateNameQueue");
    }

    @Bean
    public Queue catsUpdateBreedQueue() {
        return new Queue("catsUpdateBreedQueue");
    }

    @Bean
    public Queue catsUpdateColorQueue() {
        return new Queue("catsUpdateColorQueue");
    }

    @Bean
    public Queue catsUpdateOwnerQueue() {
        return new Queue("catsUpdateOwnerQueue");
    }


    @Bean
    public Queue catsGetAllQueue() {
        return new Queue("catsGetAllQueue");
    }

    @Bean
    public Queue catsAddFriendQueue() {
        return new Queue("catsAddFriendQueue");
    }

    @Bean
    public Queue catsRemoveQueue() {
        return new Queue("catsRemoveQueue");
    }

    @Bean
    public Binding bindCatsGetByIdQueue() {
        return BindingBuilder.bind(catsGetByIdQueue()).to(catsDirectExchange()).with("catsGetByIdQueue");
    }

    @Bean
    public Binding bindCatsCreateQueue() {
        return BindingBuilder.bind(catsCreateQueue()).to(catsDirectExchange()).with("catsCreateQueue");
    }

    @Bean
    public Binding bindCatsUpdateQueue() {
        return BindingBuilder.bind(catsUpdateQueue()).to(catsDirectExchange()).with("catsUpdateQueue");
    }

    @Bean
    public Binding bindCatsUpdateNameQueue() {
        return BindingBuilder.bind(catsUpdateNameQueue()).to(catsDirectExchange()).with("catsUpdateNameQueue");
    }

    @Bean
    public Binding bindCatsUpdateBreedQueue() {
        return BindingBuilder.bind(catsUpdateBreedQueue()).to(catsDirectExchange()).with("catsUpdateBreedQueue");
    }

    @Bean
    public Binding bindCatsUpdateColorQueue() {
        return BindingBuilder.bind(catsUpdateColorQueue()).to(catsDirectExchange()).with("catsUpdateColorQueue");
    }

    @Bean
    public Binding bindCatsUpdateOwnerQueue() {
        return BindingBuilder.bind(catsUpdateOwnerQueue()).to(catsDirectExchange()).with("catsUpdateOwnerQueue");
    }

    @Bean
    public Binding bindCatsGetAllQueue() {
        return BindingBuilder.bind(catsGetAllQueue()).to(catsDirectExchange()).with("catsGetAllQueue");
    }

    @Bean
    public Binding bindCatsAddFriendQueue() {
        return BindingBuilder.bind(catsAddFriendQueue()).to(catsDirectExchange()).with("catsAddFriendQueue");
    }

    @Bean
    public Binding bindCatsRemoveQueue() {
        return BindingBuilder.bind(catsRemoveQueue()).to(catsDirectExchange()).with("catsRemoveQueue");
    }
}