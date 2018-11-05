package com.crwal;

import com.crwal.processor.BokeYuanPageProcessor;
import com.crwal.processor.ToutiaoPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 14:23
 */
@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    BokeYuanPageProcessor bokeYuanPageProcessor;

    @Autowired
    ToutiaoPageProcessor toutiaoPageProcessor;
/*
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
*/

    /**
     * 博客园rss脚本
     */
    @Async
    @Scheduled(initialDelay = 500, fixedRate = 30*60*1000)
    public void bokeYuanJob(){
        logger.info(Thread.currentThread().getName()+"=====>>>>>博客园定时脚本启动 "+System.currentTimeMillis());
        bokeYuanPageProcessor.start();
    }

    /**
     * 博客园rss脚本
     */
    @Async
    @Scheduled(initialDelay = 500, fixedRate = 4*60*60*1000)
    public void toutiaoJob(){
        logger.info(Thread.currentThread().getName()+"=====>>>>>开发者头条定时脚本启动 "+System.currentTimeMillis());
        toutiaoPageProcessor.start();
    }
}
