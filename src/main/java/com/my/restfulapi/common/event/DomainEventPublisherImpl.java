package com.my.restfulapi.common.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DomainEventPublisherImpl implements EventPublisher{
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
