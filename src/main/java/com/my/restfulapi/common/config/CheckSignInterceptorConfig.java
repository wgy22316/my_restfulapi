package com.my.restfulapi.common.config;

import com.my.restfulapi.common.interceptor.CheckSignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CheckSignInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public CheckSignInterceptor CheckSignInterceptor(){
        return new CheckSignInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(CheckSignInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/static/**","*.html");
    }
}
