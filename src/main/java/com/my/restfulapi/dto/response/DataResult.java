package com.my.restfulapi.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataResult<T> implements Serializable {
//
//    /**
//     * 错误码
//     */
//    private String code;
//
//    /**
//     * 错误消息
//     */
//    private String msg;
//
//    /**
//     * 结果集
//     */
//    private T data;

    private static final long serialVersionUID = 6847146041806712878L;

    public DataResult() {
    }

    public DataResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public DataResult(T data) {
        this.data = data;
        this.isSuccess = true;
    }

    public DataResult(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.isSuccess = false;
    }

    public DataResult(String errorCode, String errorDesc, long elapsedMilliseconds) {
        this(errorCode, errorDesc);
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    /**
     * 是否处理成功
     */
    private boolean isSuccess;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 错误代码
     */
    private String errorCode = "";

    /**
     * 错误描述
     */
    private String errorDesc = "";

    /**
     * 处理耗时(毫秒)
     */
    private long elapsedMilliseconds;

    /**
     * 调用链id
     */
    private String requestId;
}
