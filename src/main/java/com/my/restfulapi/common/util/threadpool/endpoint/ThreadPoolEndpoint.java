package com.my.restfulapi.common.util.threadpool.endpoint;

import com.my.restfulapi.common.util.json.JsonUtil;
import com.my.restfulapi.common.util.threadpool.DynamicThreadPoolManager;
import com.my.restfulapi.common.util.threadpool.MyThreadPoolExecutor;
import com.my.restfulapi.common.util.threadpool.config.DynamicThreadPoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池监控数据
 *
 * @Author: 小师傅
 * @Date: 2021-09-05 16:18
 */
@Endpoint(id = "thread-pool")
@Component
public class ThreadPoolEndpoint {

    @Autowired
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @Autowired
    private DynamicThreadPoolProperties dynamicThreadPoolProperties;

    @ReadOperation
    public Map<String, Object> threadPools() {
        Map<String, Object> data = new HashMap<>();

        List<Map> threadPools = new ArrayList<>();
        dynamicThreadPoolProperties.getExecutors().forEach(prop -> {
            MyThreadPoolExecutor executor = dynamicThreadPoolManager.getThreadPoolExecutor(prop.getThreadPoolName());
            AtomicLong rejectCount = dynamicThreadPoolManager.getRejectCount(prop.getThreadPoolName());

            Map<String, Object> pool = new HashMap<>();

            Map config = JsonUtil.fromJson(JsonUtil.toJson(prop), Map.class);
            pool.putAll(config);
            pool.put("activeCount", executor.getActiveCount());
            pool.put("completedTaskCount", executor.getCompletedTaskCount());
            pool.put("largestPoolSize", executor.getLargestPoolSize());
            pool.put("taskCount", executor.getTaskCount());
            pool.put("rejectCount", rejectCount == null ? 0 : rejectCount.get());
            pool.put("waitTaskCount", executor.getQueue().size());
            threadPools.add(pool);
        });

        data.put("threadPools", threadPools);
        return data;
    }

}
