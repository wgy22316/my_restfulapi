package com.my.restfulapi.dto.response;

import lombok.Data;

@Data
public class DataResult {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 结果集
     */
    private Object data;

}
