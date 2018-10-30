package com.my.restfulapi.dto.request;

import lombok.Data;

@Data
public class UserInfoRequest extends BaseRequest{

    private UserInfoVo data;
}
