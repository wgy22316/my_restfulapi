package com.my.restfulapi.third.user.adapter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.my.restfulapi.common.util.RestClientUtil;
import com.my.restfulapi.third.user.BaseUserAdapter;
import com.my.restfulapi.third.user.config.MethodConfig;
import com.my.restfulapi.third.user.request.BaseUserRequest;
import com.my.restfulapi.third.user.request.UserVo;
import com.my.restfulapi.third.user.response.BaseUserResponse;
import com.my.restfulapi.third.user.response.UserDto;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

public class UserAdapter extends BaseUserAdapter {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public BaseUserResponse getUserInfo(Integer userId){
        String url = HOST + MethodConfig.USER_INFO;
        BaseUserRequest baseUserRequest = new BaseUserRequest<UserVo>();
        buildBaseRequest(baseUserRequest);
        UserVo userVo = new UserVo();
        userVo.setId(1);
        baseUserRequest.setData(userVo);

        RestClientUtil.setRestTemplate(restTemplate);

        String response = RestClientUtil.postJson(url, baseUserRequest);
        BaseUserResponse baseUserResponse = JSONObject.parseObject(response, new TypeReference<BaseUserResponse<UserDto>>(){});

        return baseUserResponse;
    }
}
