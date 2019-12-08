package com.my.restfulapi.third.feign.config;

import com.my.restfulapi.third.feign.client.IcibaFeignClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IcibaFeignConfig {

    @Bean
    public IcibaFeignClient getIcibaFeignClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(IcibaFeignClient.class, "http://fy.iciba.com/ajax.php");

    }
}
