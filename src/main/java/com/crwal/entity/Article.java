package com.crwal.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/2 10:56
 */
@Entity
@Table(name = "articles")
public class Article {
    //ID主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //标题
    @Column(name = "title")
    private String title;

    //内容
    @Column(name = "content")
    private String content;

    //URL
    @Column(name = "url")
    private String url;

    @Column(name = "gmt_create")
    private Long gmtCreate;

    @Column(name = "gmt_published")
    private Date gmtPublished;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    public SourceEum getSource() {
        return source;
    }

    public void setSource(SourceEum source) {
        this.source = source;
    }

    @Column(name = "source")
    private SourceEum source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    public Date getGmtPublished() {
        return gmtPublished;
    }

    public void setGmtPublished(Date gmtPublished) {
        this.gmtPublished = gmtPublished;
    }
}
