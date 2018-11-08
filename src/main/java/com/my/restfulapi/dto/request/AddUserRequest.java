package com.my.restfulapi.dto.request;

import lombok.Data;

@Data
public class AddUserRequest extends BaseRequest{

    private AddUserVo data;
}
