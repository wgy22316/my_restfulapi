package com.my.restfulapi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.common.util.RedisUtil;
import com.my.restfulapi.dto.request.AddUserListRequest;
import com.my.restfulapi.dto.request.AddUserListVo;
import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.mapper.dao.UserDao;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserOrderService;
import com.my.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserOrderService userOrderService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUserById(Integer id) {
        String userCacheKey = "user:" + id;
        String userCacheRes = (String) redisUtil.get(userCacheKey);
        if(!StringUtils.isEmpty(userCacheRes)){
            User user = JSONObject.parseObject(userCacheRes, User.class);
            return user;
        }

        User user = userDao.findById(id);
        if (user == null) {
            throw new BusinessException("20000", "用户不存在");
        }

        //redisUtil.set(userCacheKey, JSON.toJSONString(user));
        return user;
    }

    @Override
    public User getUserInfo(Integer id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new BusinessException("20000", "用户不存在");
        }
        return user;
    }

    @Override
    public boolean addUser(AddUserVo addUserVo) {
        String userName = addUserVo.getUserName();
        String userCode = addUserVo.getUserCode();

        User user1 = new User();
        user1.setUserName(userName);
        user1.setUserCode(userCode);
        int result = userDao.save(user1);
        return result > 0 ? true : false;
    }

    @Override
    public boolean batchAddUser(AddUserListRequest request) {
        AddUserListVo addUserListVo = request.getData();
        List<AddUserVo> addUserVoList = addUserListVo.getAddUserVoList();
        if (CollectionUtils.isEmpty(addUserVoList)) {
            return false;
        }

        List<User> userList = new ArrayList<>();
        addUserVoList.forEach((AddUserVo addUserVo) -> {
            String userCode = addUserVo.getUserCode();
            String userName = addUserVo.getUserName();

            User user = new User();
            user.setUserName(userName);
            user.setUserCode(userCode);
            userList.add(user);

        });
        userDao.batchSave(userList);
        return false;
    }

    @Override
    public boolean delUserById(Integer id) {
        int result = userDao.deleteById(id);
        return result > 0;
    }

    @Override
    public PageInfo<User> userInfoList() {
        PageHelper.startPage(1, 2);
        //List<User> userList = userMapper.selectAll();
        String ids = "1,2,3,4,5,6";
        List<User> userList = userDao.findByIds(ids);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        Page page = (Page) userList;
        System.out.println(page);
        return userPageInfo;
    }

    @Async
    public ListenableFuture<Boolean> isNewCustomerFuture() {
        return new AsyncResult<>(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser2() {
        User user1 = new User();
        user1.setUserName("hhhhh");
        user1.setUserCode("uuuu");
        userDao.save(user1);
        try {
            userOrderService.addUserOrderService();
        }catch (Exception e){
            e.printStackTrace();
        }


        //userOrderService.addUserOrderService();
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser3() {
        User user1 = new User();
        user1.setUserName("hhhhh");
        user1.setUserCode("uuuu");
        userDao.save(user1);
        userOrderService.addUserOrderService();
        return false;
    }
}
