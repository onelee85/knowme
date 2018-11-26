package com.crawl.recommender;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.dic.LearnTool;
import org.ansj.domain.Nature;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:09
 * 分词工具
 */
public class TfidfUtil {

    public static Result split(String text)
    {
        return ToAnalysis.parse(text);
    }
    public static KeyWordComputer kwc = new KeyWordComputer(10);
    /**
     *
     * @param title 文本标题
     * @param content 文本内容
     * @param keyNums 返回的关键词数目
     * @return
     */
    public static List<Keyword> getTFIDE(String title, String content, int keyNums){
        return kwc.computeArticleTfidf(title, content);
    }

    /**
     *
     * @param content 文本内容
     * @param keyNums 返回的关键词数目
     * @return
     */
    public static List<Keyword> getTFIDE(String content,int keyNums){
        return kwc.computeArticleTfidf(content);
    }

    public static List<String> getKeywords(String content,int keyNums){
        List<String> keywords = new ArrayList<>(keyNums);
        //System.out.println(NlpAnalysis.parse(content));
        Result r = NlpAnalysis.parse(content);
        r.getTerms().forEach((item) -> {
                    if(item.getNatureStr().equals("n")
                            || item.getNatureStr().equals("en")
                            || item.getNatureStr().equals("nz")){
                        //System.out.print(item.getRealName()+", ");
                        keywords.add(item.getRealName());
                    }
                }
        );
        return keywords;
    }

    static void analysisTest(){
        String str = "大型互联网公司必考java面试题与面试技巧" ;
        System.out.println(BaseAnalysis.parse(str));
        System.out.println(ToAnalysis.parse(str));
        System.out.println(DicAnalysis.parse(str));
        System.out.println(IndexAnalysis.parse(str));
        System.out.println(NlpAnalysis.parse(str));
        Result r = NlpAnalysis.parse(str);
        r.getTerms().forEach((item) -> {
                    if(item.getNatureStr().equals("n") || item.getNatureStr().equals("en") ){
                        System.out.print(item.getRealName()+", ");
                    }
                }
        );
    }

    static void learnToolTest(){
        //构建一个新词学习的工具类。这个对象。保存了所有分词中出现的新词。出现次数越多。相对权重越大。
        LearnTool learnTool = new LearnTool() ;

        //进行词语分词。也就是nlp方式分词，这里可以分多篇文章
        NlpAnalysis.parse("说过，社交软件也是打着沟通的平台，让无数寂寞男女有了肉体与精神的寄托。") ;
        NlpAnalysis.parse("其实可以打着这个需求点去运作的互联网公司不应只是社交类软件与可穿戴设备，还有携程网，去哪儿网等等，订房订酒店多好的寓意") ;
        NlpAnalysis.parse("张艺谋的卡宴，马明哲的戏") ;
        //learnTool.addTerm(new NewWord());


        //取得学习到的topn新词,返回前10个。这里如果设置为0则返回全部
        System.out.println(learnTool.getTopTree(10));

        //只取得词性为Nature.NR的新词
        System.out.println(learnTool.getTopTree(10, Nature.NR));
    }

    public static void  main(String[] args){
        //learnToolTest();
        KeyWordComputer kwc = new KeyWordComputer(10);
        String title = "mongodb 故障排查";
        String content = "mongodb 故障排查";
        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
        System.out.println(result);
    }
}
