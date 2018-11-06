package com.my.restfulapi.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.common.util.RequestUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.my.restfulapi.controller..*.*(..))")
    public void apiLog() {

    }

    @Around("apiLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String clasName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestUrl = request.getRequestURL().toString();

        JSONObject requestJsonParams = RequestUtil.getJSONParam(request);

        String ip = request.getRemoteAddr();
        String errCode = "";
        String msg = "";
        Exception resultExeption = null;
        long startTime = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                errCode = ((BusinessException) e).getErrCode();
                msg = e.getMessage();

            }

            resultExeption = e;
        }

        long runTime = System.currentTimeMillis() - startTime;
        String requestParamJson = JSON.toJSONString(requestJsonParams);

        String reponseResult = JSON.toJSONString(result);
//        logger.info("apiLog=>requestUrl:{}, requestClass:{}, requestMethod:{}, requestParam:{}, ip:{}, errCode:{}, " +
//                        "msg:{}, response:{}, runTime:{}",
//                requestUrl,
//                clasName,
//                methodName,
//                requestParamJson,
//                ip,
//                errCode,
//                msg,
//                reponseResult,
//                runTime
//        );

        logger.info("apiLog=>requestUrl:" + requestUrl +
                        ", requestClass:" + clasName +
                        ", requestMethod:" + methodName +
                        ", requestParam:" + requestParamJson +
                        ", ip:" + ip +
                        ", errCode:" + errCode +
                        ", msg:" + msg +
                        ", response:" + reponseResult +
                        ", runTime:" + runTime
        );
        if (resultExeption != null) {
            throw resultExeption;
        }

        return result;
    }


}
