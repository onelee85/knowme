package com.crawl.processor;

import com.crawl.entity.Article;
import com.crawl.entity.SourceEum;
import com.crawl.service.ArticleService;
import com.jayway.jsonpath.JsonPath;
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
 * csdn 最新博客
 */
@Component
public class CsdnPageProcessor implements PageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CsdnPageProcessor.class);

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .addHeader("Host","blog.csdn.net")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/59.0")
            .addHeader("Accept","text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Cookie","uuid_tt_dd=10_28867322940-1540…5ac=1541492999; dc_tos=phrjly")
            .addHeader("Upgrade-Insecure-Requests","1")
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
        //System.out.println(page.getJson());
        count =0;
        List<Selectable> nodes = page.getJson().jsonPath("$.articles[*]").nodes();
        List<Article> datas = new ArrayList<>(nodes.size());
        for (Selectable s : nodes) {
            String title = JsonPath.read(s.get(), "$.title").toString();
            String url = JsonPath.read(s.get(), "$.url").toString();
            logger.info(title+">>>>"+url);
            Article article = new Article();
            article.setTitle(title);
            article.setUrl(url);
            article.setSource(SourceEum.CSDN);
            datas.add(article);
            count++;
        }
        articleService.addArticles(datas);
    }

    //private final static String URL = "https://blog.csdn.net/nav/newarticles";
    private final static String URL = "https://blog.csdn.net/api/articles?type=more&category=newarticles&shown_offset=";
    //推荐文章
    private final static String REC_URL = "https://blog.csdn.net/api/articles?type=new&category=home";

    public void start(){
        long startTime, endTime;
        logger.info("开始爬取CSDN博客...");
        startTime = System.currentTimeMillis();
        String url = URL+System.currentTimeMillis()+"000";
        Spider.create(this).addUrl(url).thread(1).setExitWhenComplete(Boolean.TRUE).run();
        Spider.create(this).addUrl(REC_URL).thread(1).setExitWhenComplete(Boolean.TRUE).run();
        endTime = System.currentTimeMillis();
        logger.info("CSDN博客爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }


    public static void main(String[] args) {
        new CsdnPageProcessor().start();
    }
}
