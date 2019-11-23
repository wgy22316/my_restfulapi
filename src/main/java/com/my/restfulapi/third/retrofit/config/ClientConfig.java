package com.my.restfulapi.third.retrofit.config;

import com.my.restfulapi.common.util.helper.retrofit.factory.RetrofitFactory;
import com.my.restfulapi.third.retrofit.client.IcibaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    public static final String ICIBA_BASE_URL = "http://fy.iciba.com/ajax.php";

    @Bean
    public IcibaClient icibaClient(RetrofitFactory retrofitFactory){
        return retrofitFactory.newInstance(ICIBA_BASE_URL, IcibaClient.class);
    }
}
