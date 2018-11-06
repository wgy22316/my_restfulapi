package com.my.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.my.restfulapi.mapper")
@SpringBootApplication
public class RestfulApiApplication {

//    @Autowired
//    private RestTemplateBuilder templateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate(){
//        return templateBuilder.build();
//    }

}
