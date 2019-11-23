package com.my.restfulapi.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.common.util.async.AsyncHelper;
import com.my.restfulapi.dto.request.AddUserListRequest;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.response.DataResult;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AsyncHelper asyncHelper;

    @GetMapping("ping")
    public DataResult ping(){
//        String str = JSON.toJSONString("");
//        String str2 = "";
//        String str3 = JSON.parseObject(str, String.class);
//        System.out.println(str3);
//        System.out.println(str);
//        if("".equals(str)){
//            System.out.println("hello");
//        }else {
//            System.out.println("world");
//
//        }

        List<Integer> list = new ArrayList<>();
        String jsonList = JSON.toJSONString(list);
        System.out.println(jsonList);

        List list1 = JSON.parseObject(jsonList, new TypeReference<List<Integer>>(){});
        if(CollectionUtils.isEmpty(list1)){
            System.out.println("222");
        }

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
    public DataResult getUserById(@RequestBody @Validated UserInfoRequest userInfoRequest){
        User user = userService.getUserById(userInfoRequest.getData().getId());
        //asyncHelper.withAsync(this::sayHello);
        sayHello();
        return DataResultUtil.success(user);
    }

    @PostMapping("/addUserList")
    public DataResult addUserList(@RequestBody AddUserListRequest addUserListRequest){
        userService.batchAddUser(addUserListRequest);
        return DataResultUtil.success();
    }

    @PostMapping("delUserById")
    public DataResult delUserById(@Param("id") Integer id){
        userService.delUserById(id);
        return DataResultUtil.success();
    }

    @PostMapping("/userInfoList")
    public DataResult getUserById(){
        PageInfo<User> userPageInfo = userService.userInfoList();
        return DataResultUtil.success(userPageInfo);
    }

    private void sayHello(){
        logger.info("hello");
        int i = 100 / 0;
        System.out.println("hello");
    }
}
