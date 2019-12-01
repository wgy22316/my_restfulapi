package com.my.restfulapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("BaseRequest")
public class BaseRequest {
    /**
     * 请求账号:appId
     */
    @ApiModelProperty(notes = "appid", required = true, example = "1003")
    private String appId;

    /**
     * 请求唯一Id
     */
    @ApiModelProperty(notes = "唯一请求id",example = "201912011111")
    private String requestId;

    /**
     * 时间戳
     */
    @ApiModelProperty("timestamp")
    private long timestamp;

    /**
     * 语言 (eg: en-US, zh-CN)
     */
    @ApiModelProperty("language")
    private String language;

    /**
     * 时区(eg: GMT+8)
     */
    @ApiModelProperty("timeZone")
    private String timeZone;

    /**
     * 签名值
     */
    @ApiModelProperty("sign")
    private String sign;
}
