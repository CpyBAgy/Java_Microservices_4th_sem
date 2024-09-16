package com.CpyBAgy.javarush.RabbitMQConfiguration;

import com.CpyBAgy.javarush.CoreModels.CatModels.*;
import com.CpyBAgy.javarush.CoreModels.OtherModels.Color;
import com.CpyBAgy.javarush.CoreModels.OtherModels.FilteringType;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerAddCatModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerCreateModel;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerDTO;
import com.CpyBAgy.javarush.CoreModels.OwnerModels.OwnerUpdateModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserChangePasswordModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserDTO;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserSignUpModel;
import com.CpyBAgy.javarush.CoreModels.UserModels.UserUpdateModel;
import com.CpyBAgy.javarush.ResponseModels.CatResponseModel;
import com.CpyBAgy.javarush.ResponseModels.OwnerResponseModel;
import com.CpyBAgy.javarush.ResponseModels.UserResponseModel;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitMQConfiguration {

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(5432);

        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");

        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        //create custom json message converter
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("CatAddFriendModelModel", CatAddFriendModel.class);
        idClassMapping.put("CatCreateModel", CatCreateModel.class);
        idClassMapping.put("CatDTO", CatDTO.class);
        idClassMapping.put("CatUpdateModel", CatUpdateModel.class);
        idClassMapping.put("CatUpdateOwnerModel", CatUpdateOwnerModel.class);
        idClassMapping.put("Color", Color.class);
        idClassMapping.put("FilteringType", FilteringType.class);
        idClassMapping.put("OwnerDTO", OwnerDTO.class);
        idClassMapping.put("OwnerCreateModel", OwnerCreateModel.class);
        idClassMapping.put("OwnerUpdateModel", OwnerUpdateModel.class);
        idClassMapping.put("OwnerAddCatModel", OwnerAddCatModel.class);
        idClassMapping.put("UserDTO", UserDTO.class);
        idClassMapping.put("UserSignUpModel", UserSignUpModel.class);
        idClassMapping.put("UserUpdateModel", UserUpdateModel.class);
        idClassMapping.put("UserChangePasswordModel", UserChangePasswordModel.class);
        idClassMapping.put("UserResponseModel", UserResponseModel.class);
        idClassMapping.put("OwnerResponseModel", OwnerResponseModel.class);
        idClassMapping.put("CatResponseModel", CatResponseModel.class);

        typeMapper.setIdClassMapping(idClassMapping);
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }
}