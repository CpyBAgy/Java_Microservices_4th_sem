package com.CpyBAgy.javarush.Application;

import com.CpyBAgy.javarush.DataAccess.UserRepository;
import com.CpyBAgy.javarush.DataAccessModels.User;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.CpyBAgy.javarush")
@EntityScan("com.CpyBAgy.javarush.DataAccessModels")
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, User.class})
@EnableRabbit
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
