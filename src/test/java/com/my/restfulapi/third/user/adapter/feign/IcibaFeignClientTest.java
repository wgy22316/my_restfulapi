package com.my.restfulapi.third.user.adapter.feign;

import com.my.restfulapi.RestfulApiApplicationTests;
import com.my.restfulapi.third.feign.client.IcibaFeignClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IcibaFeignClientTest extends RestfulApiApplicationTests {

    @Autowired
    private IcibaFeignClient icibaClient;

    @Test
    public void get(){
        System.out.println(icibaClient.get());
    }
}
