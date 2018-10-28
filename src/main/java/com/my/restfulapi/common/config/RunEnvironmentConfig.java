package com.my.restfulapi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.profiles")
@Data
public class RunEnvironmentConfig {

    private String active;
}
