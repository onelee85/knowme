package com.crawl.dao;

import com.crawl.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:58
 */
public interface ArticleDao extends JpaRepository<Article, Long> {

    @Query(value="SELECT * FROM articles a WHERE a.url = :url", nativeQuery = true)
    Article getArticleByUrl(@Param("url") String url);

    @Query(value="select * from articles where gmt_create >= :gmt_create and gmt_create < :gmt_create order by  view_time desc limit 1000", nativeQuery = true)
    List<Article> findArticleByDate(@Param("gmt_create") Date gmtCreate);
}
