package com.crwal.service;

import com.crwal.dao.ArticleDao;
import com.crwal.dao.EventDao;
import com.crwal.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 10:40
 */
@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventDao eventDao;
    @Autowired
    private ArticleDao articleDao;

    public void add(Event events) {
        if(articleDao.existsById(events.getaId()) &&
                eventDao.getEventsByUserAndArticleID(events.getUserId(), events.getaId(), events.getPreferDegree()).size() == 0){
            eventDao.save(events);
        }
    }
}
