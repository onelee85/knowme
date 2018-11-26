package com.crawl.dao;

import com.crawl.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:23
 */
public interface RecommendationDao extends JpaRepository<Recommendation, Long> {

    @Query(value="SELECT * FROM recommendations a WHERE a.user_id= :user_id and a.a_id = :aid", nativeQuery = true)
    Recommendation getRecommendationByUid(@Param("user_id") long userId, @Param("aid") long aId);
}