package com.my.restfulapi.common.util.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class AsyncHelper {

    @Async
    public void withAsync(Runnable runnable) {
        runnable.run();
    }

    @Async
    public <T> T withAsync(Supplier<T> supplier) {
        return supplier.get();
    }
}
