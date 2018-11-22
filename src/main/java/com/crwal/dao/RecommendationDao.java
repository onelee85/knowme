package com.crwal.dao;

import com.crwal.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:23
 */
interface RecommendationDao extends JpaRepository<Recommendation, Long> {
}