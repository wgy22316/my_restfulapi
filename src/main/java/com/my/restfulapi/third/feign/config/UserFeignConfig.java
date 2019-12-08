package com.my.restfulapi.third.feign.config;

import com.my.restfulapi.third.feign.client.UserFeignClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFeignConfig {

    @Bean
    public UserFeignClient getUserFeignClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logLevel(Logger.Level.FULL)
                .target(UserFeignClient.class, "http://127.0.0.1:8090");

    }
}
