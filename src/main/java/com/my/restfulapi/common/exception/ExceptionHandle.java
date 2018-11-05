package com.my.restfulapi.common.exception;

import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.dto.response.DataResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResult handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return DataResultUtil.error(businessException.getErrCode(), businessException.getMessage());
        } else if (e instanceof BindException) {
            return DataResultUtil.error("2001", e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            return DataResultUtil.error("2000", e.getMessage());
        } else {
            return DataResultUtil.error("9999", e.getMessage());
        }
    }
}
