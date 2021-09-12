package com.my.restfulapi.common.event.handler;

import com.my.restfulapi.common.event.BaseEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {
    @EventListener
    public void handleEvent(BaseEvent event) {
        //doSomething
        System.out.println("UserEventHandler.handleEvent");
    }
}
