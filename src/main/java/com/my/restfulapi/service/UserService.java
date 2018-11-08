package com.my.restfulapi.service;


import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.model.User;

import java.util.List;

public interface UserService {

    User getUserInfo(Integer id);

    boolean addUserList(AddUserVo addUserVo);
}
