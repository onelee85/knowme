package com.crawl.dao;

import com.crawl.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:58
 */
public interface ArticleDao extends JpaRepository<Article, Long> {

    @Query(value="SELECT * FROM articles a WHERE a.url = :url", nativeQuery = true)
    Article getArticleByUrl(@Param("url") String url);
}
