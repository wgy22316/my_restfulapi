package com.my.restfulapi.common.event;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEvent<T> implements Serializable {
    private static final long serialVersionUID = 1465328245048581896L;

    /**
     * 事件id
     */
    private String eventId;

    /**
     * 发生时间
     */
    private LocalDateTime occurredOn;

    /**
     * 事件数据
     */
    private T data;

    public BaseEvent(String eventId, T data) {
        this.eventId = eventId;
        this.data = data;
        this.occurredOn = LocalDateTime.now();
    }
}
