package com.my.restfulapi.controller;


import com.my.restfulapi.common.utils.DataResultUtil;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.response.DataResult;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUserInfo")
    public DataResult userDetail(@RequestBody UserInfoRequest userInfoRequest) {
        //全局异常捕获
        User user = userService.getUserInfo(userInfoRequest.getData().getId());
        return DataResultUtil.success(user);
    }

}
