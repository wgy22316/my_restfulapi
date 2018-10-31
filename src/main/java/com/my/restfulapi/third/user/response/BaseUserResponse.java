package com.my.restfulapi.third.user.response;

import lombok.Data;

@Data
public class BaseUserResponse<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 结果对象
     */
    private T data;
}
