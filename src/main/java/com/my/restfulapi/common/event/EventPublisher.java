package com.my.restfulapi.common.event;

public interface EventPublisher {
    /**
     * 发布事件
     *
     * @param event 领域事件
     */
    void publishEvent(BaseEvent event);
}
