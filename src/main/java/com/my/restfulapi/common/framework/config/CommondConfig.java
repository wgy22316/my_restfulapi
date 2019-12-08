package com.my.restfulapi.common.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="data-config.filter-config")
public class CommondConfig {
    private LogConfig logConfig;

    @Data
    public static class LogConfig {
        /**
         * 以逗号分隔
         */
        private String ignoreMethodName = "";
    }
}
