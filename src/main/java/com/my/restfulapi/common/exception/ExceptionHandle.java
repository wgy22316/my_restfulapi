package com.my.restfulapi.common.exception;

import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.dto.response.DataResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResult handle(Exception e) {
        System.out.println("Exception");
//        if (e instanceof BusinessException) {
//            BusinessException businessException = (BusinessException) e;
//            return DataResultUtil.error(businessException.getErrCode(), businessException.getMessage());
//        } else if (e instanceof BindException) {
//            FieldError fieldError = ((BindException) e).getFieldError();
//            return DataResultUtil.error("2001", fieldError.getDefaultMessage());
//        } else if (e instanceof MethodArgumentNotValidException) {
//            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
//            FieldError fieldError = bindingResult.getFieldError();
//            return DataResultUtil.error("2000", fieldError.getDefaultMessage());
//        } else {
//            return DataResultUtil.error("9999", e.getMessage());
//        }

        return DataResultUtil.error("9999", "服务内部错误");
    }

    /**
     * 参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public DataResult handle(MethodArgumentNotValidException e) {
        System.out.println("MethodArgumentNotValidException");
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        return DataResultUtil.error("2000", fieldError.getDefaultMessage());
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public DataResult handle(BusinessException e) {
        System.out.println("BusinessException");
        return DataResultUtil.error(e.getErrCode(), e.getMessage());
    }
}
