package com.my.restfulapi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="data-config.filter-config")
public class FilterConfig {
    private LogConfig logConfig;

    @Data
    public static class LogConfig {
        /**
         * 以逗号分隔
         */
        private String ignoreMethodName = "";
    }
}
