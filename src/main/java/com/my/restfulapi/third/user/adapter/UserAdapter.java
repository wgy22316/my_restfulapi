package com.my.restfulapi.third.user.adapter;

import com.my.restfulapi.third.user.BaseUserAdapter;
import com.my.restfulapi.third.user.config.MethodConfig;

public class UserAdapter extends BaseUserAdapter {

    private String getUserInfo(Integer userId){
        String url = MethodConfig.USER_INFO;
        return "";
    }
}
