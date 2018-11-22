package com.crawl.controller;

import com.crawl.entity.Article;
import com.crawl.recommender.ContentBasedRecommender;
import com.crawl.recommender.TfidfUtil;
import com.crawl.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/6 13:45
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    /**
     * 文章列表
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("page") int page,@RequestParam("size") int size) {
        Map<String, Object> result = new HashMap<>();
        Page<Article> pages = articleService.findArticles(page, size);
        result.put("code", 1);
        result.put("data",pages.getContent());
        result.put("pages",pages.getTotalPages());
        result.put("total",pages.getTotalElements());
        result.put("number",pages.getNumber());
        for (Article article : pages.getContent()){
            article.setTags(TfidfUtil.getKeywords(article.getTitle(), 5).toString());
        }
        return result;
    }


    @Autowired
    ContentBasedRecommender contentBasedRecommender;

    @RequestMapping("/recommend")
    public Map<String, Object> recommend(@RequestParam("page") int page,@RequestParam("size") int size) {
        Map<String, Object> result = new HashMap<>();
        Page<Article> pages = articleService.findArticles(page, size);
        result.put("data",pages.getContent());
        result.put("pages",pages.getTotalPages());
        result.put("total",pages.getTotalElements());
        result.put("number",pages.getNumber());
        for (Article article : pages.getContent()){
            article.setTags(TfidfUtil.getKeywords(article.getTitle(), 5).toString());
        }
        result.put("code", 1);
        return result;
    }



}