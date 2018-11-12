package com.crwal.entity;

import javax.persistence.*;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/12 15:29
 */
@Entity
@Table(name = "event_logs")
public class Event {
    //ID主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "a_id")
    private Long aId;

    @Column(name = "view_time")
    private Long viewTime;

    /**
     *  0：浏览，1：点赞，2：收藏
     */
    @Column(name = "prefer_degree")
    private int preferDegree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Long getViewTime() {
        return viewTime;
    }

    public void setViewTime(Long viewTime) {
        this.viewTime = viewTime;
    }

    public int getPreferDegree() {
        return preferDegree;
    }

    public void setPreferDegree(int preferDegree) {
        this.preferDegree = preferDegree;
    }
}
