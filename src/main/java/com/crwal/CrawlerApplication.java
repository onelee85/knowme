package com.crwal;

import com.crwal.config.TaskThreadPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: jiao.li
 * Date: 2018/11/2 13:41
 */
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class} )
@SpringBootApplication
public class CrawlerApplication  extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrawlerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
