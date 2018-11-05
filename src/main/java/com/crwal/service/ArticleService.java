package com.crwal.service;

import com.crwal.dao.ArticleDao;
import com.crwal.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addMusic(Article article) {
        articleDao.save(article);
    }

    public void addMusics(List<Article> articles) {
        articleDao.saveAll(articles);
    }
}
