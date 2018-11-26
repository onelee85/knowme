package com.crawl.Schedule;

import com.crawl.controller.UserController;
import com.crawl.processor.BokeYuanPageProcessor;
import com.crawl.processor.CsdnPageProcessor;
import com.crawl.processor.ToutiaoPageProcessor;
import com.crawl.recommender.ContentBasedRecommender;
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
public class RecommendTasks {
    private static final Logger logger = LoggerFactory.getLogger(RecommendTasks.class);

    @Autowired
    ContentBasedRecommender contentBasedRecommender;

    /**
     * 根据用户阅读记录提取文章标签 1小时更新一次
     */
   /* @Async
    @Scheduled(cron = "0 1 * * * *")*/
    @Async
    @Scheduled(initialDelay = 200, fixedRate = 60*60*1000)
    public void refreshUserPreferLabel(){
        logger.info(Thread.currentThread().getName()+"=====>>>>>refreshUserPreferLabel启动 "+System.currentTimeMillis());
        contentBasedRecommender.setUserPreferLabels(UserController.USER_ID);
    }

    /**
     * 文章推荐 60分钟更新一次
     */
    @Async
    @Scheduled(initialDelay = 1000, fixedRate = 30*60*1000)
    public void refreshArticleRecommend(){
        logger.info(Thread.currentThread().getName()+"=====>>>>>refreshArticleRecommend启动 "+System.currentTimeMillis());
        contentBasedRecommender.recommend(UserController.USER_ID);
    }

    /**
     * 用户喜好标签point衰减 每日一次
     */

    /**
     * 清除15日前未读和不喜欢的推荐文章
     */
}
