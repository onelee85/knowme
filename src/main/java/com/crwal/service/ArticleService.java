package com.crwal.service;

import com.crwal.dao.ArticleDao;
import com.crwal.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 10:40
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public void addArticle(Article article) {
        Example<Article> example = Example.of(article);
        if (!articleDao.findOne(example).isPresent())
            articleDao.save(article);
    }

    public void addArticles(List<Article> articles) {
        for(Article article : articles){
            addArticle(article);
        }

        //articleDao.saveAll(articles);
    }
}
