package com.my.restfulapi.dao.impl;

import com.my.restfulapi.common.dao.impl.BaseDao;
import com.my.restfulapi.dao.UserDao;
import com.my.restfulapi.model.User;
import org.springframework.stereotype.Service;

@Service("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao {
}
