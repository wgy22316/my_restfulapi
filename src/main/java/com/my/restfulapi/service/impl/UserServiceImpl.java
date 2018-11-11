package com.my.restfulapi.service.impl;

import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.mapper.UserMapper;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserById(Integer id){
        User user = findById(id);
        if(user == null){
            throw new BusinessException("20000", "用户不存在");
        }

        AddUserVo addUserVo = new AddUserVo();
        addUserVo.setUserCode("hello1");
        addUserVo.setUserName("hello2");
        addUserList(addUserVo);

        return user;
    }

//    @Override
//    public User getUserInfo(Integer id) {
//        User user = userMapper.selectByPrimaryKey(id);
//        if (user == null) {
//            throw new BusinessException("20000", "用户不存在");
//        }
//        return user;
//    }

    @Override
    public boolean addUserList(AddUserVo addUserVo) {
        String userName = addUserVo.getUserName();
        String userCode = addUserVo.getUserCode();

        User user1 = new User();

        user1.setUserName(userName);
        user1.setUserCode(userCode);

        User user2 = new User();

        user2.setUserName(userName);
        user2.setUserCode(userCode);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        int result = 0;
//        int result = userMapper.insertList(userList);
        //int result = userDao.batchSave(userList);
        return result > 0 ? true : false;
    }
}
