package com.crawl.recommender;

import com.crawl.Schedule.CrawlProcessTasks;
import com.crawl.entity.*;
import com.crawl.service.ArticleService;
import com.crawl.service.EventService;
import com.crawl.service.RecommendService;
import com.crawl.service.UserService;
import com.crawl.utils.JacksonUtil;
import com.crawl.utils.Similarity;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:06
 */
@Component
public class ContentBasedRecommender {

    private static final Logger logger = LoggerFactory.getLogger(ContentBasedRecommender.class);

    @Autowired
    EventService eventService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    RecommendService recommendService;

    /**
     * 设置用户喜好标签
     * @param userId
     */
    public void setUserPreferLabels(Long userId){
        //获取用户阅读 关注 喜好流水记录
        Date end = Calendar.getInstance().getTime();
        Calendar Cal=java.util.Calendar.getInstance();
        Cal.setTime(end);
        Cal.add(java.util.Calendar.HOUR_OF_DAY,-1);
        //删选一个小时内的标签
        Date begin = Cal.getTime();
        List<Event> events = eventService.getUserLabels(userId, begin, end);
        PerferEum[] points = PerferEum.values();
        Map<String, Integer> userPointsMap = new HashMap<>();
        events.forEach(event -> {
            //通过流水获取阅读的文章
            long aid = event.getaId();
            Article ar = articleService.getId(aid);
            if(ar != null && StringUtils.isNotEmpty(ar.getTitle())){
                //解析文章title 获得关键字
                List<String> keywords = getKeywords(ar.getTitle());

                //根据用户行为权重打分
                int point = points[event.getPreferDegree()].getPoint();

                keywords.forEach(key ->{
                    //userPointsMap.put(key, point);
                    userPointsMap.putIfAbsent(key, point);
                    userPointsMap.computeIfPresent(key, (k, v) -> v+point);
                });
                //logger.info("{} : {},",keywords, ar.getTitle());
            }
        });
        logger.info("userPointsMap: {}",userPointsMap);
        if(userPointsMap.size() > 0){
            //获取用户表标签
            User user = userService.getById(userId);
            Map<Object, Object> userKeywords = getUserKeywords(user.getPrefList());
            logger.info("user keys {}",userKeywords);
            //更新标签
            userPointsMap.forEach((key,value) ->{
                userKeywords.putIfAbsent(key, value);
                userKeywords.computeIfPresent(key, (k, v) -> (int)v+value);
            });
            logger.info("userKeywords: {}",userKeywords);
            updateUserKeywords(user, userKeywords);
        }
    }

    public List<String> getKeywords(String sentence){
        return TfidfUtil.getKeywords(sentence, 5);
    }

    /**
     * 解析用户喜好列表
     * @param preferList
     * @return
     */
    public Map<Object, Object> getUserKeywords(String preferList){
        Map<Object, Object> userKeywords = new HashMap<>();
        if(StringUtils.isEmpty(preferList)) return userKeywords;
        //解析Json
        userKeywords = JacksonUtil.json2Map(preferList);
        return userKeywords;
    }

    /**
     * 更新用户喜好列表
     * @param user
     * @param userKeywords
     */
    public void updateUserKeywords(User user, Map<Object, Object> userKeywords){
        String preferList = JacksonUtil.map2Json(userKeywords);
        user.setPrefList(preferList);
        userService.Update(user);
    }

    /**
     * 更新用户文章
     * @param userId
     */
    public void recommend(Long userId){
        //一段时间内的文章
        Page<Article> pages = articleService.findArticles(1, 1000);
        List<Article> articles = pages.getContent();
        if(articles.size() == 0) return;

        Map<Long, List<String>> articleKeywords = new HashMap<>();
        Map<Long, Integer> articlePoints = new TreeMap<>();
        //文章的标签
        articles.forEach(a ->{
            String title = a.getTitle();
            Long id = a.getId();
            List<String> keywords = getKeywords(title);
            articleKeywords.put(id, keywords);
        });
        //获取用户兴趣标签
        User user = userService.getById(userId);
        Map<Object, Object> userKeywords = getUserKeywords(user.getPrefList());
        logger.info("user keys {}",userKeywords);
        //计算匹配的文章
        articleKeywords.forEach((Long aid, List<String> as) ->{
            int point = 0;
            for (String articleKeyword : as){
                if(userKeywords.containsKey(as)){
                    point += (int)userKeywords.get(articleKeyword);
                }
            }
            if(point >0){
                articlePoints.put(aid, point);
            }

        });
        logger.info("articlePoints {}",articlePoints);
        //过滤已推荐过的文章
        //排序后前10篇文章推荐给用户
        articlePoints.forEach((Long aid, Integer point) ->{
            Recommendation recommendation = new Recommendation();
            recommendation.setaId(aid);
            recommendation.setUserId(userId);
            recommendation.setFeedback(0);
            recommendService.addRecommendation(recommendation);
        });

    }


}
