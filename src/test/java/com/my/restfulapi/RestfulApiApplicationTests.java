package com.my.restfulapi;

import com.my.restfulapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulApiApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserService userService;

//    @Test
//    public void testTddUserList() {
//        AddUserVo addUserVo = new AddUserVo();
//        addUserVo.setUserName("hello");
//        addUserVo.setUserCode("hello");
//
//        boolean result = userService.addUserList(addUserVo);
//        System.out.println(result);
//    }
}
