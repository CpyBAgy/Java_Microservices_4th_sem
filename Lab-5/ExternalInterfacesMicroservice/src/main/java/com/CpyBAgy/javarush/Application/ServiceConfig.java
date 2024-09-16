package com.CpyBAgy.javarush.Application;

import com.CpyBAgy.javarush.CoreModels.OtherModels.DefaultAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultAdmin defaultAdmin() { return new DefaultAdmin("admin", "admin"); }
}
