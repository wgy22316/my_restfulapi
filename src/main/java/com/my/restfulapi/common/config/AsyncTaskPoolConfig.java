package com.my.restfulapi.common.config;

import com.my.restfulapi.common.util.ThrowableUtil;
import com.my.restfulapi.common.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncTaskPoolConfig implements AsyncConfigurer {
    private final Logger logger = LoggerFactory.getLogger(AsyncTaskPoolConfig.class);

//    @Bean("taskExecutor")
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
//        //核心线程数10：线程池创建时候初始化的线程数
//        threadPoolExecutor.setCorePoolSize(10);
//        //最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
//        threadPoolExecutor.setMaxPoolSize(20);
//        //缓冲队列200：用来缓冲执行任务的队列
//        threadPoolExecutor.setQueueCapacity(200);
//        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
//        threadPoolExecutor.setKeepAliveSeconds(60);
//        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
//        threadPoolExecutor.setThreadNamePrefix("taskExecutor-");
//        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
//        // 该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
//        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，
//        //这样这些异步任务的销毁就会先于Redis线程池的销毁
//        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
//        //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，
//        //以确保应用最后能够被关闭，而不是阻塞住
//        threadPoolExecutor.setAwaitTerminationSeconds(60);
//        return threadPoolExecutor;
//    }

    @Override
    public Executor getAsyncExecutor() {
        //在方法getAsyncExecutor()中创建线程池的时候，必须使用 executor.initialize()，

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        //核心线程数10：线程池创建时候初始化的线程数
        threadPoolExecutor.setCorePoolSize(10);
        //最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        threadPoolExecutor.setMaxPoolSize(20);
        //缓冲队列200：用来缓冲执行任务的队列
        threadPoolExecutor.setQueueCapacity(200);
        //允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        threadPoolExecutor.setKeepAliveSeconds(60);
        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        threadPoolExecutor.setThreadNamePrefix("taskExecutor-");
        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
        // 该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，
        //这样这些异步任务的销毁就会先于Redis线程池的销毁
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，
        //以确保应用最后能够被关闭，而不是阻塞住
        threadPoolExecutor.setAwaitTerminationSeconds(60);
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.warn("Method[{}], Params[{}], Async Handle fail, ===> [{}]", method.getName(),
                JsonUtil.toJson(params), ThrowableUtil.getStackTrace(ex));
    }
}
