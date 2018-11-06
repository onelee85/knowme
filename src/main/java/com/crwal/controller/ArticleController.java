package com.crwal.controller;

import com.crwal.entity.Article;
import com.crwal.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/6 13:45
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam("page") int page,@RequestParam("size") int size) {
        Map<String, Object> result = new HashMap<>();
        Page<Article> pages = articleService.findArticles(page, size);
        result.put("data",pages.getContent());
        result.put("pages",pages.getTotalPages());
        result.put("total",pages.getTotalElements());
        result.put("number",pages.getNumber());
        return result;
    }
}