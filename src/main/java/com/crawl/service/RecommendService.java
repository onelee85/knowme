package com.crawl.service;

import com.crawl.dao.RecommendationDao;
import com.crawl.entity.Recommendation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 10:40
 */
@Service
public class RecommendService {
    private static final Logger logger = LoggerFactory.getLogger(RecommendService.class);

    @Autowired
    private RecommendationDao recommendationDao;

    public Boolean addRecommendation(Recommendation recommendation) {
        if(recommendationDao.getRecommendationByUid(recommendation.getUserId(), recommendation.getaId()) == null ){
            recommendationDao.save(recommendation);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void addRecommendations(List<Recommendation> recommendations) {
        for(Recommendation Recommendation : recommendations){
            addRecommendation(Recommendation);
        }
    }

    public Page<Recommendation> findArticles(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page,size,sort);
        return recommendationDao.findAll(pageable);
    }
}
