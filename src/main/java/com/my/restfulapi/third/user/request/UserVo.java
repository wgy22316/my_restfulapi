package com.my.restfulapi.third.user.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    /**
     * 用户id
     */
    private Integer id;
}
