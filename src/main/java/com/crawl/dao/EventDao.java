package com.crawl.dao;


import com.crawl.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:58
 */
public interface EventDao extends JpaRepository<Event, Long> {

    @Query(value="SELECT * FROM event_logs a WHERE a.user_id = :user_id and a.a_id = :a_id and a.prefer_degree = :prefer", nativeQuery = true)
    List<Event> getEventsByUserAndArticleID(@Param("user_id") long userId, @Param("a_id") long aId, @Param("prefer") long prefer);


    @Query(value="select * from event_logs a where a.user_id = :user_id and a.view_time >= :begin_time and a.view_time < :end_time  order by view_time desc limit 100;", nativeQuery = true)
    List<Event> getEventsByUserAndDate(@Param("user_id") long userId,  @Param("begin_time") Date beginTime,  @Param("end_time") Date endTime);


}
