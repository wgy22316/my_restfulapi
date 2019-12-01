package com.my.restfulapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户信息查询Request实体")
public class UserInfoRequest extends BaseRequest{

    @Valid
    @NotNull(message = "data不能为空")
    @ApiModelProperty("data信息")
    private UserInfoVo data;
}
