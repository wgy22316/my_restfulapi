package com.my.restfulapi.controller;

import com.alibaba.fastjson.JSON;
import com.my.restfulapi.common.util.RequestSignUtil;
import com.my.restfulapi.dto.request.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void addUser(){
        AddUserListRequest request = new AddUserListRequest();
        AddUserListVo addUserListVo = new AddUserListVo();
        List<AddUserVo> addUserVoList = new ArrayList<>();

        AddUserVo addUserVo = new AddUserVo();
        addUserVo.setUserName("1-1");
        addUserVo.setUserCode("1-1-name");
        addUserVoList.add(addUserVo);

        AddUserVo addUserVo1 = new AddUserVo();
        addUserVo1.setUserName("1-2");
        addUserVo1.setUserCode("1-2-name");
        addUserVoList.add(addUserVo1);

        addUserListVo.setAddUserVoList(addUserVoList);
        request.setData(addUserListVo);
        RequestSignUtil.buildBaseRequest(request);
        System.out.println(JSON.toJSON(request));

    }
}