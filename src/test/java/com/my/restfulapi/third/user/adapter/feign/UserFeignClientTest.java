package com.my.restfulapi.third.user.adapter.feign;
import com.my.restfulapi.common.util.RequestSignUtil;
import com.my.restfulapi.dto.request.UserInfoVo;

import com.my.restfulapi.RestfulApiApplicationTests;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.third.feign.client.UserFeignClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFeignClientTest extends RestfulApiApplicationTests {
    @Autowired
    private UserFeignClient userFeignClient;

    @Test
    public void ping(){
        System.out.println(userFeignClient.ping());
    }

    @Test
    public void getUserInfoById(){
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(1);
        userInfoRequest.setData(userInfoVo);

        RequestSignUtil.buildBaseRequest(userInfoRequest);
        System.out.println(userFeignClient.getUserInfoById(userInfoRequest));
    }

    @Test
    public void delUserById(){
        System.out.println(userFeignClient.delUserById(18));
    }
}
