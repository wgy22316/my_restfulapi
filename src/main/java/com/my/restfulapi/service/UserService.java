package com.my.restfulapi.service;


import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.model.User;

public interface UserService {

//    User getUserInfo(Integer id);

    boolean addUserList(AddUserVo addUserVo);

    User getUserById(Integer id);
}
