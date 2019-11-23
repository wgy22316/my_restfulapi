package com.my.restfulapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.common.util.RedisUtil;
import com.my.restfulapi.dto.request.AddUserListRequest;
import com.my.restfulapi.dto.request.AddUserListVo;
import com.my.restfulapi.dto.request.AddUserVo;
import com.my.restfulapi.mapper.UserMapper;
import com.my.restfulapi.model.User;
import com.my.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUserById(Integer id){
//        String userCacheKey = "user:" + id;
//        String userCacheRes = (String) redisUtil.get(userCacheKey);
//        if(!StringUtils.isEmpty(userCacheRes)){
//            User user = JSONObject.parseObject(userCacheRes, User.class);
//            return user;
//        }

        User user = findById(id);
        if(user == null){
            throw new BusinessException("20000", "用户不存在");
        }

        //redisUtil.set(userCacheKey, JSON.toJSONString(user));
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
        userMapper.insertList(userList);
        //userDao.batchSave(userList);
        return result > 0 ? true : false;
    }

    @Override
    public boolean batchAddUser(AddUserListRequest request) {
        AddUserListVo addUserListVo = request.getData();
        List<AddUserVo> addUserVoList = addUserListVo.getAddUserVoList();
        if(CollectionUtils.isEmpty(addUserVoList)){
            return false;
        }

        List<User> userList = new ArrayList<>();
        addUserVoList.forEach((AddUserVo addUserVo)->{
            String userCode = addUserVo.getUserCode();
            String userName = addUserVo.getUserName();

            User user = new User();
            user.setUserName(userName);
            user.setUserCode(userCode);
            userList.add(user);

        });
        userMapper.insertList(userList);
        return false;
    }

    @Override
    public boolean delUserById(Integer id) {
        //userMapper.deleteByPrimaryKey(id);
        User user = new User();
        user.setId(8);
        user.setUserCode("20000000000000000000000000000000000000");
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    @Override
    public PageInfo<User> userInfoList() {
        PageHelper.startPage(1,2);
        //List<User> userList = userMapper.selectAll();
        String ids = "1,2,3,4,5,6";
        List<User> userList = userMapper.selectByIds(ids);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        Page page = (Page) userList;
        System.out.println(page);
        return userPageInfo;
    }
}
