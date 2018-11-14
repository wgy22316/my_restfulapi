package com.my.restfulapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class UserInfoVo {

    /**
     * 用户id
     */
    @Min(value = 1, message = "id必须大于0")
    private Integer id;
}
