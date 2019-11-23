package com.my.restfulapi.common.aspect;

import com.my.restfulapi.common.config.RunEnvironmentConfig;
import com.my.restfulapi.common.exception.BusinessException;
import com.my.restfulapi.common.util.RequestSignUtil;
import com.my.restfulapi.common.util.RequestUtil;
import com.my.restfulapi.dto.request.BaseRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
@Aspect
@Order(1)
public class CheckSignAspect {

    @Autowired
    private RunEnvironmentConfig runEnvironmentConfig;

    @Pointcut("@annotation(com.my.restfulapi.common.annotation.CheckSign)")
    public void checkSignPointCut() {

    }

    @Before("checkSignPointCut()")
    public void checkSign(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (Objects.isNull(args) || args.length <= 0) {
            return;
        }

        for (Object obj : args) {
            if (obj instanceof BaseRequest) {
                BaseRequest baseSignRequest = (BaseRequest) obj;
                if (!RequestUtil.checkBaseRequestParam(baseSignRequest)) {
                    throw new BusinessException("20000", "参数错误(缺少公共参数)");
                }

                //请求时间是否超时
                if (!RequestUtil.checkTimestamp(baseSignRequest)) {
                    throw new BusinessException("20001", "请求时间戳超时");
                }

                //appid 是否是分配的appid 同时获取appid对应的密钥
                String appKey = RequestUtil.checkAppId(baseSignRequest);
                if (StringUtils.isEmpty(appKey)) {
                    throw new BusinessException("20002", "非法appId");
                }

                if (!RequestSignUtil.checkSign(baseSignRequest, appKey)) {
                    throw new BusinessException("20003", "签名错误");
                }
                break;
            }
        }
    }
}
