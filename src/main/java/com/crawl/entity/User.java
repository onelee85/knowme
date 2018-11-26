package com.crawl.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/12 15:29
 */
@Entity
@Table(name = "users")
public class User {
    //ID主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pref_list")
    private String prefList;

    @Column(name = "latest_log_time")
    private Date latestTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefList() {
        return prefList;
    }

    public void setPrefList(String prefList) {
        this.prefList = prefList;
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }
}
