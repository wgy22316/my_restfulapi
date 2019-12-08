package com.my.restfulapi.common.framework.aspect;

import com.alibaba.fastjson.JSON;
import com.my.restfulapi.common.framework.config.CommondConfig;
import com.my.restfulapi.common.util.ThrowableUtil;
import com.my.restfulapi.common.util.log4j2.MyLogger;
import com.my.restfulapi.dto.response.DataResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class RequestLogAspect {

    private static final Logger logger = MyLogger.getInstance(RequestLogAspect.class);
    @Autowired
    private CommondConfig commondConfig;

    @Pointcut("execution(public * com.my.restfulapi.controller..*.*(..))")
    public void requestLogPointcut() {

    }

    /**
     * ProceedingJoinPoint 只能用在around切面方法
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("requestLogPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StringBuilder stringBuilder = buildRequestArgs(proceedingJoinPoint);
        long startTime = System.currentTimeMillis();
        try {
            Object result = proceedingJoinPoint.proceed();
            buildSuccessResultLog(result, stringBuilder, startTime);
            return result;
        } catch (Exception e) {
//            if (e instanceof BusinessException) {
//                errCode = ((BusinessException) e).getErrCode();
//                msg = e.getMessage();
//
//            }
            buildFailResultLog(e, stringBuilder);
            throw e;
        }
    }

    private StringBuilder buildRequestArgs(ProceedingJoinPoint joinPoint) {
        StringBuilder sBuilder = new StringBuilder();
        if (isIgnoreLogMethod(joinPoint)) {
            return sBuilder;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof MultipartFile) {
                    args = ArrayUtils.remove(args, i);
                    continue;
                }
            }
        }
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        sBuilder.append("requestIp=>").append(request.getRemoteAddr());
        sBuilder.append(", className=>").append(className);
        sBuilder.append(", method=>" + method.getName());
        sBuilder.append(", args=>");
        buildArgsLog(args, sBuilder);

        return sBuilder;
    }

    /**
     * 请求方法是否记录请求日志
     *
     * @param joinPoint
     * @return
     */
    private boolean isIgnoreLogMethod(ProceedingJoinPoint joinPoint) {
        String ignoreMethodName = commondConfig.getLogConfig().getIgnoreMethodName();
        if (StringUtils.isNotBlank(ignoreMethodName)) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String methodName = signature.getMethod().getName();
            List<String> methodNameList = Arrays.asList(StringUtils.split(ignoreMethodName, ","));
            return methodNameList.contains(methodName);
        }
        return false;
    }

    private void buildArgsLog(Object[] args, StringBuilder sBuilder) {
        if (Objects.isNull(args) || args.length <= 0) {
            sBuilder.append("null,");
            return;
        }
        Arrays.stream(args).filter(Objects::nonNull).forEach(val -> sBuilder.append(JSON.toJSONString(val)).append(','));
    }

    private void buildSuccessResultLog(Object retObj, StringBuilder stringBuilder, Long startTime) {
        long runTime = System.currentTimeMillis() - startTime;
        if(retObj instanceof DataResult){
            DataResult dataResult = (DataResult) retObj;
            dataResult.setElapsedMilliseconds(runTime);
            stringBuilder.append(" result=>" + JSON.toJSONString(dataResult));
        }else if (retObj instanceof String) {
            stringBuilder.append(" result=>" + retObj);
        } else {
            stringBuilder.append(" result=>" + JSON.toJSONString(retObj));
        }

        stringBuilder.append(", runTime=>").append(runTime);
        logger.info("请求日志: {}", stringBuilder.toString());
    }

    private void buildFailResultLog(Exception e, StringBuilder stringBuilder) {
        stringBuilder.append("exception result=>").append(ThrowableUtil.getStackTrace(e));
        logger.info("请求日志: {}", stringBuilder.toString());
    }

}
