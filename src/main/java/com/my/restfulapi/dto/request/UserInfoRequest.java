package com.my.restfulapi.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoRequest extends BaseRequest{

    @Valid
    @NotNull(message = "data不能为空")
    private UserInfoVo data;
}
