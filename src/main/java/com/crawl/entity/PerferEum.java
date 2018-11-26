package com.crawl.entity;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/5 15:56
 * 0：浏览，1：点赞，2：收藏
 */
public enum PerferEum {
    view(1), love(2), favorite(3);

    int point;

    PerferEum(int point){
        this.point = point;
    }
    public int getPoint(){
        return this.point;
    }
}
