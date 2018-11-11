package com.my.restfulapi.service.impl;

import com.my.restfulapi.RestfulApiApplicationTests;
import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplTest extends RestfulApiApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void addUserList() {
        AddUserVo addUserVo = new AddUserVo();
        addUserVo.setUserName("hello");
        addUserVo.setUserCode("hello");

        boolean result = userService.addUserList(addUserVo);
        System.out.println(result);
    }
}