package com.crawl.processor;

import com.crawl.entity.Article;
import com.crawl.entity.SourceEum;
import com.crawl.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客园RSS
 */
@Component
public class BokeYuanPageProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BokeYuanPageProcessor.class);

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

    @Autowired
    ArticleService articleService;
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//*entry").nodes();
        List<Article> datas = new ArrayList<>(nodes.size());
        count =0;
        for (Selectable s : nodes) {
            //获取页面需要的内容
            Article article = new Article();
            logger.info(s.xpath("//published/text()") + "  : " + s.xpath("//title[@type=\"text\"]/text()") + " >>>>> " + s.xpath("//id/text()"));
            article.setTitle(s.xpath("//title[@type=\"text\"]/text()").get());
            article.setUrl(s.xpath("//id/text()").get());
            article.setSource(SourceEum.bokeyuan);
            datas.add(article);
            count++;
        }
        articleService.addArticles(datas);
    }

    private final static String URL = "http://feed.cnblogs.com/blog/sitehome/rss";
    public void start(){
        long startTime, endTime;
        logger.info("开始爬取博客园RSS...");
        startTime = System.currentTimeMillis();
        Spider.create(this).addUrl(URL).thread(2).setExitWhenComplete(Boolean.TRUE).run();
        endTime = System.currentTimeMillis();
        logger.info("博客园RSS爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }


    public static void main(String[] args) {
        new BokeYuanPageProcessor().start();
    }
}
