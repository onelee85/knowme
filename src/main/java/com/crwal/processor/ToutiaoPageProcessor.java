package com.crwal.processor;

import com.crwal.entity.Article;
import com.crwal.entity.SourceEum;
import com.crwal.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开发者头条每日爬取
 */
@Component
public class ToutiaoPageProcessor implements PageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BokeYuanPageProcessor.class);

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setDomain("toutiao.io")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    ;
    private static int count =0;

    public static final String list = "http://toutiao.io/prev/[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static final String item = "http://toutiao.io/posts/\\w+";


    public Site getSite() {
        return site;
    }

    @Autowired
    ArticleService articleService;

    public void process(Page page) {
        count =0;
       List<Selectable> nodes = page.getHtml().xpath("//*div[@class=\"post\"]").nodes();
       List<Article> datas = new ArrayList<>(nodes.size());
        for (Selectable s : nodes) {
            //获取页面需要的内容
            logger.info(s.xpath("//*div[@class=\"content\"]/h3/a/text()") + " >>>>> " + "https://toutiao.io" + s.xpath("//*[@class=\"content\"]/h3/a/@href"));
            Article article = new Article();
            article.setTitle(s.xpath("//*div[@class=\"content\"]/h3/a/text()").get());
            article.setUrl("https://toutiao.io" + s.xpath("//*[@class=\"content\"]/h3/a/@href").get());
            article.setSource(SourceEum.kaifazhetoutiao);
            datas.add(article);
            count++;
        }
        articleService.addArticles(datas);
    }
    private final static String URL = "http://toutiao.io/prev/";

    public void start(){
        long startTime, endTime;
        logger.info("开始爬取开发者头条...");
        startTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Spider.create(this).addUrl(URL + sdf.format(new Date())).thread(1).setExitWhenComplete(Boolean.TRUE).run();
        endTime = System.currentTimeMillis();
        logger.info("爬取开发者头条结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }

    public static void main(String[] args) {
        new ToutiaoPageProcessor().start();
    }
}
