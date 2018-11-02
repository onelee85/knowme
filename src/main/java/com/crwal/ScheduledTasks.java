package com.crwal;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 14:23
 */
@Component
public class ScheduledTasks {

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用cron  "+System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedRate "+System.currentTimeMillis());
    }

    @Async
    @Scheduled(initialDelay = 500, fixedRate = 10*60*1000)
    public void cronJob(){
        System.out.println(Thread.currentThread().getName()+"=====>>>>>cron执行  "+System.currentTimeMillis());
    }
}
