package com.my.restfulapi.common.util.log4j2;

import lombok.Data;

import java.util.List;

@Data
public class LogData {

    /**
     * 调用结果
     */
    private Object result;

    /**
     * 服务器执行耗时
     */
    private long elapsedMilliseconds;

    /**
     * 调用方完整信息
     */
    private String invocation;

    /**
     * Rest服务参数校验失败的错误信息
     */
    private List<String> paramCheckErrors;

    /**
     * 服务调用发生异常时，完整的异常信息
     */
    private String errorStack;

    /**
     * Rest服务的请求url
     */
    private String restUrl;

    /**
     * 调用方的IP
     */
    private String remoteIp;

    /**
     * 主机IP
     */
    private String hostIp;

    /**
     * 链路Id
     */
    private String traceId;
}
