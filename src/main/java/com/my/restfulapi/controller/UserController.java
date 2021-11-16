package com.my.restfulapi.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.my.restfulapi.common.framework.annotation.CheckSign;
import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.common.util.async.AsyncHelper;
import com.my.restfulapi.common.util.log4j2.MyLogger;
import com.my.restfulapi.common.util.threadpool.DynamicThreadPoolManager;
import com.my.restfulapi.common.util.threadpool.MyThreadPoolExecutor;
import com.my.restfulapi.dto.request.AddUserListRequest;
import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.response.DataResult;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("用户操作相关")
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
    private MyLogger logger = MyLogger.getInstance(UserController.class);

    //private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AsyncHelper asyncHelper;

    //ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Resource
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @ApiOperation(value = "服务ping接口")
    @GetMapping("ping")
    public DataResult ping() {
        return DataResultUtil.success("running");
    }

    @PostMapping("/getUserInfo")
    @CheckSign
    public DataResult userDetail(@RequestBody UserInfoRequest userInfoRequest) {
        //全局异常捕获
        User user = userService.getUserInfo(userInfoRequest.getData().getId());
        return DataResultUtil.success(user);
    }

    @ApiOperation(value = "根据用户Id查询用户信息的接口")
    //@ApiImplicitParam(name = "userInfoRequest", value = "用户信息参数", required = true,paramType = "UserInfoRequest")
    @PostMapping("/getUserInfoById")
    //@CheckSign
    public DataResult getUserById(@RequestBody @Validated UserInfoRequest userInfoRequest) {
        User user = userService.getUserById(userInfoRequest.getData().getId());
        //asyncHelper.withAsync(this::sayHello);

        log.info(JSON.toJSONString(user));
        sayHello();
        return DataResultUtil.success(user);
    }

    @ApiOperation(value = "批量添加用户")
    @PostMapping("/addUserList")
    public DataResult addUserList(@RequestBody AddUserListRequest addUserListRequest) {
        userService.batchAddUser(addUserListRequest);
        return DataResultUtil.success();
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("delUserById")
    public DataResult delUserById(@Param("id") Integer id) {
        userService.delUserById(id);
        return DataResultUtil.success();
    }

    @ApiOperation(value = "获取用户列表")
    @PostMapping("/userInfoList")
    public DataResult getUserById() {
        PageInfo<User> userPageInfo = userService.userInfoList();
        return DataResultUtil.success(userPageInfo);
    }

    private void sayHello() {
        System.out.println("hello");
    }

    @PostMapping("/test1")
    public DataResult testRequestTransection1() {
//        for (int i = 0; i < 10; i++) {
//            userService.isNewFuture();
//        }
//        userService.addUser2();

        logger.info("testRequestTransection1");
        MyThreadPoolExecutor myThreadPoolExecutor = dynamicThreadPoolManager.getThreadPoolExecutor("test1");

        for (int i = 0; i < 1000; i++) {
//            executorService.submit(() -> {
//                logger.info(System.currentTimeMillis() + "_hhhhhhh");
//            });

//            executorService.submit(new MDCRunnable(()->{
//                logger.info(System.currentTimeMillis() + "_hhhhhhh");
//            }));


            myThreadPoolExecutor.submit(() -> {
                logger.info(System.currentTimeMillis() + "_hhhhhhh");
            });

        }

        return DataResultUtil.success();
    }


    @PostMapping("/test2")
    public DataResult testRequestTransection2(){
        userService.addUser3();
        return DataResultUtil.success();
    }

    @PostMapping("/test3")
    public DataResult testRequestTransection3(){
        userService.addUser4();
        return DataResultUtil.success();
    }

    @PostMapping("/test4")
    public DataResult testRequestTransection4(){
        userService.addUser5();
        return DataResultUtil.success();
    }
}
