package com.crwal.dao;

import com.crwal.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:58
 */
public interface ArticleDao extends JpaRepository<Article, Long> {
}
