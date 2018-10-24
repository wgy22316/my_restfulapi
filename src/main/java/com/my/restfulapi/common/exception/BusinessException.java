package com.my.restfulapi.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String errCode;

    private String message;

    public BusinessException(String errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }
}
