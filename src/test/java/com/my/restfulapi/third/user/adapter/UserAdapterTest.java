package com.my.restfulapi.third.user.adapter;

import com.alibaba.fastjson.JSON;
import com.my.restfulapi.third.user.response.BaseUserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAdapterTest {


    @Test
    public void testGetUserInfo(){
        UserAdapter userAdapter = new UserAdapter();
        BaseUserResponse baseUserResponse = userAdapter.getUserInfo(1);
        System.out.println(JSON.toJSONString(baseUserResponse));
    }
}