package com.crwal.dao;


import com.crwal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:58
 */
public interface EventDao extends JpaRepository<Event, Long> {

    @Query(value="SELECT * FROM event_logs a WHERE a.user_id = :user_id and a.a_id = :a_id and a.prefer_degree = :prefer", nativeQuery = true)
    List<Event> getEventsByUserAndArticleID(@Param("user_id") long userId, @Param("a_id") long aId, @Param("prefer") long prefer);
}
