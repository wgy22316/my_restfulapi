package com.my.restfulapi.dto.request;

import lombok.Data;

@Data
public class AddUserListRequest extends  BaseRequest{
    private AddUserListVo data;
}
