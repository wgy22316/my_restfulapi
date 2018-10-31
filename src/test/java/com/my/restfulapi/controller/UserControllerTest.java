package com.my.restfulapi.controller;

import com.alibaba.fastjson.JSON;
import com.my.restfulapi.common.utils.RequestSignUtil;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.request.UserInfoVo;
import org.junit.Test;

public class UserControllerTest {

    @Test
    public void userDetail() {
        UserInfoRequest userInfoRequest = new UserInfoRequest();

        UserInfoVo data = new UserInfoVo();
        data.setId(1);
        userInfoRequest.setData(data);

        RequestSignUtil.buildBaseRequest(userInfoRequest);
        System.out.println(JSON.toJSONString(userInfoRequest));
    }
}