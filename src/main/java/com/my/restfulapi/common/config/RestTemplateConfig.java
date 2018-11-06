package com.my.restfulapi.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

//conditionalOnClass 标识ProxyConfig类存在时解析这个类
@Configuration
//@ConditionalOnClass(ProxyConfig.class)
public class RestTemplateConfig {
    @Value("${rest.readTimeOut}")
    private int readTimeout;
    @Value("${rest.connectTimeOut}")
    private int connectionTimeout;
    @Autowired
    private ProxyConfig proxyConfig;

    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(readTimeout);
        httpRequestFactory.setConnectTimeout(connectionTimeout);

        if (proxyConfig.getEnabled()) {
            SocketAddress address = new InetSocketAddress(proxyConfig.getHost(), proxyConfig.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpRequestFactory.setProxy(proxy);
        }

        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
        RestTemplate restTemplate = new RestTemplate(httpClientFactory);
        //实现自己的rest请求异常处理，避免resttemplate请求非200状态码抛出异常
        //restTemplate.setErrorHandler(new RestResponseErrorHandler());
        return restTemplate;
    }
}
