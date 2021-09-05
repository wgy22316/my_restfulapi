package com.my.restfulapi.common.util.threadpool.alarm;

/**
 * @Author: 小师傅
 * @Date: 2021-09-05 17:05
 */
public interface ThreadPoolAlarmNotify {

    /**
     * 告警通知
     * @param alarmMessage
     */
    void alarmNotify(AlarmMessage alarmMessage);
}
