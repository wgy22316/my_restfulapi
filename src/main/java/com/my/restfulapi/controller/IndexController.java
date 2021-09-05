package com.my.restfulapi.controller;

import com.my.restfulapi.common.util.DataResultUtil;
import com.my.restfulapi.common.util.threadpool.DynamicThreadPoolManager;
import com.my.restfulapi.common.util.threadpool.MyThreadPoolExecutor;
import com.my.restfulapi.dto.response.DataResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class IndexController {

//    @Autowired
//    private DataSource dataSource;
//
//    @RequestMapping("/index")
//    @ResponseBody
//    public String index() throws SQLException {
//        System.out.println(dataSource.getConnection());
//        System.out.println(dataSource);
//        return "hello spring boot";
//    }

    @Resource
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @GetMapping("threadPoolTest")
    public DataResult threadPoolTest() {
        MyThreadPoolExecutor myThreadPoolExecutor1 = dynamicThreadPoolManager.getThreadPoolExecutor("test1");
        MyThreadPoolExecutor myThreadPoolExecutor2 = dynamicThreadPoolManager.getThreadPoolExecutor("test2");

        for (int i = 0; i < 10000; i++) {
            AtomicLong atomicLong = new AtomicLong();
            myThreadPoolExecutor1.submit(() -> {
                atomicLong.incrementAndGet();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 10000; i++) {
            AtomicLong atomicLong = new AtomicLong();
            myThreadPoolExecutor2.submit(() -> {
                atomicLong.incrementAndGet();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        return DataResultUtil.success();
    }
}
