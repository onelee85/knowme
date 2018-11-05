package com.crwal.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 开发者头条每日爬取
 */
public class ToutiaoPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static int count =0;


    public Site getSite() {
        return site;
    }

    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//*div[@class=\"post\"]").nodes();
        System.out.println(nodes.size());
        for (Selectable s : nodes) {
            //获取页面需要的内容
            //System.out.println(s.get());
            System.out.println(s.xpath("//*div[@class=\"content\"]/h3/a/text()") + " >>>>> " + "https://toutiao.io" + s.xpath("//*[@class=\"content\"]/h3/a/@href"));
            count++;
        }
    }

    public void start(){
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(this).addUrl("https://toutiao.io/prev/2018-10-31").thread(2).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+count+"条记录");
    }

    public static void main(String[] args) {
        new ToutiaoPageProcessor().start();
    }
}
