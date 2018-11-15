package com.my.restfulapi.controller;


import com.my.restfulapi.common.annotation.CheckSign;
import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.response.DataResult;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("ping")
    public DataResult ping(){
        return DataResultUtil.success("running");
    }

//    @PostMapping("/getUserInfo")
//    @CheckSign
//    public DataResult userDetail(@RequestBody UserInfoRequest userInfoRequest) {
//        //全局异常捕获
//        User user = userService.getUserInfo(userInfoRequest.getData().getId());
//        return DataResultUtil.success(user);
//    }

    @PostMapping("/getUserInfoById")
    @CheckSign
    public DataResult getUserById(@RequestBody @Validated UserInfoRequest userInfoRequest){
        User user = userService.getUserById(userInfoRequest.getData().getId());
        return DataResultUtil.success(user);
    }

}
