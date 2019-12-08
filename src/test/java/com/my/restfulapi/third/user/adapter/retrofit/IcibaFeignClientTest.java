package com.my.restfulapi.third.user.adapter.retrofit;

import com.my.restfulapi.RestfulApiApplication;
import com.my.restfulapi.common.util.helper.retrofit.RetrofitHelper;
import com.my.restfulapi.third.retrofit.client.IcibaClient;
import com.my.restfulapi.third.retrofit.response.TranslationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestfulApiApplication.class)
public class IcibaFeignClientTest {
    @Autowired
    private RetrofitHelper retrofitHelper;
    @Autowired
    private IcibaClient icibaClient;

    @Test
    public void testGet(){
        TranslationResponse translationResponse = retrofitHelper.execute(icibaClient.get());
        System.out.println(translationResponse);
    }
}
