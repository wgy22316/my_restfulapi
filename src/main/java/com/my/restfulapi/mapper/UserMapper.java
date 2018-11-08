package com.my.restfulapi.mapper;

import com.my.restfulapi.model.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>,MySqlMapper<User> {

}