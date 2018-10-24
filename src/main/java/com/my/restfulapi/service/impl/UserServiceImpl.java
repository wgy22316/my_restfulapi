package com.my.restfulapi.service.impl;

import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.mapper.UserMapper;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException("20000", "用户不存在");
        }
        return user;
    }
}
