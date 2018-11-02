package com.crwal.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 博客园RSS
 */
public class BokeYuanPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .addHeader("Host","feed.cnblogs.com")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/59.0")
            .addHeader("Accept","text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
            .addHeader("Accept-Encoding","gzip, deflate")
            //.addHeader("X-Requested-With","XMLHttpRequest")
            //.addHeader("Content-Type","application/x-www-form-urlencoded")
            .addHeader("Referer","www.cnblogs.com")
            .setRetryTimes(3)
            .setTimeOut(3000)
            .setSleepTime(1000)
            .setCycleRetryTimes(3);

    private static int count =0;


    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        //System.out.println(page.getHtml());
        List<Selectable> nodes = page.getHtml().xpath("//*entry").nodes();
        System.out.println(nodes.size());
        for (Selectable s : nodes) {
            //获取页面需要的内容
            //System.out.println(s.get());
            System.out.println(s.xpath("//published/text()") + "  : " + s.xpath("//title[@type=\"text\"]/text()") + " >>>>> " + s.xpath("//id/text()"));
            count++;
        }
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(new BokeYuanPageProcessor()).addUrl("http://feed.cnblogs.com/blog/sitehome/rss").thread(2).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }
}
