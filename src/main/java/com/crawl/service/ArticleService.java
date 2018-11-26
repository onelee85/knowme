package com.crawl.service;

import com.crawl.dao.ArticleDao;
import com.crawl.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
            //article.setGmtCreate(System.currentTimeMillis());
            articleDao.save(article);
        }
    }

    public void addArticles(List<Article> articles) {
        for(Article article : articles){
            addArticle(article);
        }

        //articleDao.saveAll(articles);
    }

    public Page<Article> findArticles(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page,size,sort);
        return articleDao.findAll(pageable);
    }

    public Article getArticleByUrl(String url){
        if(url == null) return null;
        return articleDao.getArticleByUrl(url);
    }

    public Article getId(long id){
        return articleDao.findById(id).get();
    }

    public List<Article> findArticlesByDate(Date date){
        return articleDao.findArticleByDate(date);
    }
}
