package com.crwal.service;

import com.crwal.dao.ArticleDao;
import com.crwal.entity.Article;
import com.crwal.processor.BokeYuanPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 10:40
 */
@Service
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleDao articleDao;

    public void addArticle(Article article) {
/*        Example<Article> example = Example.of(article);
        Article a = getArticleByUrl(article.getUrl());
        logger.debug("article id :{}", a.getId());
        if (!articleDao.findOne(example).isPresent()){
            article.setGmtModified(new Date());
            articleDao.save(article);
        }*/
        if (articleDao.getArticleByUrl(article.getUrl()) == null){
            article.setGmtModified(new Date());
            articleDao.save(article);
        }
    }

    public void addArticles(List<Article> articles) {
        for(Article article : articles){
            addArticle(article);
        }

        //articleDao.saveAll(articles);
    }

    public Article getArticleByUrl(String url){
        if(url == null) return null;
        return articleDao.getArticleByUrl(url);
    }
}
