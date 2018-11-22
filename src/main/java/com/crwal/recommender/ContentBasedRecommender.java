package com.crwal.recommender;

import com.crwal.utils.Similarity;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.springframework.stereotype.Component;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:06
 */
@Component
public class ContentBasedRecommender {

    public static void main(String[] args) throws Exception {
        //testSmi("部署在");
        //testDemo();
        //test();
        testKeywords();
    }
    static String[] sentences =
            new String[] {"SpringBoot jar包如何部署在nginx上",
                        "大型互联网公司必考java面试题与面试技巧",
                        "一次堆内存溢出问题分析——虚拟机优化",
                        "基于SpringCloud的微服务架构演变史？",
                        "Spring Boot启动配置原理",
                        };

    public static void testDemo() {
        JiebaSegmenter segmenter = new JiebaSegmenter();

        for (String sentence : sentences) {
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
            System.out.println(segmenter.sentenceProcess(sentence).toString());
        }
    }

    public static void test(){
        for (String sentence : sentences) {
            System.out.println(TfidfUtil.getTFIDE(sentence, 5));;
        }
    }

    public static void testSmi(String content){
        for (String sentence : sentences) {
            System.out.println(content+"<>"+sentence+":    "+Similarity.sim(content, sentence));
        }

    }

    public static void testKeywords(){
        for (String sentence : sentences) {
            System.out.println(TfidfUtil.getKeywords(sentence, 5));;
        }
    }
}
