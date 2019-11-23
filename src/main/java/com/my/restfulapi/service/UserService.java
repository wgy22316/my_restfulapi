package com.my.restfulapi.service;


import com.github.pagehelper.PageInfo;
import com.my.restfulapi.dto.request.AddUserListRequest;
import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.model.User;

public interface UserService {

//    User getUserInfo(Integer id);

    boolean addUserList(AddUserVo addUserVo);

    User getUserById(Integer id);

    boolean batchAddUser(AddUserListRequest request);

    boolean delUserById(Integer id);

    PageInfo<User> userInfoList();
}
