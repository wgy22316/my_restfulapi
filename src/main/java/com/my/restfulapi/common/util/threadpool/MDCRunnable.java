package com.my.restfulapi.common.util.threadpool;

import org.slf4j.MDC;

import java.util.Map;

/**
 * @Author: 小师傅
 * @Date: 2021-11-16 10:43
 */
public class MDCRunnable implements Runnable {
    private final Runnable runnable;

    private final Map<String, String> map;

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
        // 保存当前线程的MDC值
        this.map = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        // 传入已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
        // 装饰器模式，执行run方法
        runnable.run();
        // 移除已保存的MDC值
        for (Map.Entry<String, String> entry : map.entrySet()) {
            MDC.remove(entry.getKey());
        }
    }
}
