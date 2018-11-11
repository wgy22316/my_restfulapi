package com.my.restfulapi;

import com.my.restfulapi.common.mapper.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.my.restfulapi.mapper"}, markerInterface = MyMapper.class)
@SpringBootApplication
public class RestfulApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
    }

}
