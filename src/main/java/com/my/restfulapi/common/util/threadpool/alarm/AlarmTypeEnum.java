package com.my.restfulapi.common.util.threadpool.alarm;

/**
 * @Author: 小师傅
 * @Date: 2021-09-05 17:07
 */
public enum AlarmTypeEnum {
    /**
     * 钉钉
     */
    DING_TALK("DingTalk"),
    /**
     * 外部系统
     */
    EXTERNAL_SYSTEM("ExternalSystem");

    AlarmTypeEnum(String type) {
        this.type = type;
    };

    private String type;

    public String getType() {
        return type;
    }
}
