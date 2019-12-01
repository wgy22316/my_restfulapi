package com.my.restfulapi.common.filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegisterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TraceFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("traceFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
