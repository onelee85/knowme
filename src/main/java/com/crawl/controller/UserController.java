package com.crawl.controller;

import com.crawl.entity.Event;
import com.crawl.entity.PerferEum;
import com.crawl.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/6 13:45
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EventService eventService;

    //测试用户ID
    public final static long USER_ID = 10000L;
    /**
     * 浏览
     * @param id 文章ID
     * @return
     */
    @RequestMapping("/view")
    public Map<String, Object> view(@RequestParam("id") long id) {
        Map<String, Object> result = new HashMap<>();
        saveUserEvent(id, PerferEum.view.ordinal());
        result.put("code", 1);
        return result;
    }

    /**
     * 点赞
     * @param id 文章ID
     * @return
     */
    @RequestMapping("/love")
    public Map<String, Object> love(@RequestParam("id") long id) {
        Map<String, Object> result = new HashMap<>();
        saveUserEvent(id, PerferEum.love.ordinal());
        result.put("code", 1);
        return result;
    }

    /**
     * 收藏
     * @param id 文章ID
     * @return
     */
    @RequestMapping("/favorite")
    public Map<String, Object> favorite(@RequestParam("id") long id) {
        Map<String, Object> result = new HashMap<>();
        saveUserEvent(id, PerferEum.favorite.ordinal());
        result.put("code", 1);
        return result;
    }

    private void saveUserEvent(long aid, int prefer){
        Event events = new Event();
        events.setaId(aid);
        events.setUserId(USER_ID);
        events.setPreferDegree(prefer);
        eventService.add(events);
    }
}