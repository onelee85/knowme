package com.crwal;

import com.crwal.config.TaskThreadPoolConfig;
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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrawlerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CrawlerApplication.class, args);
    }
}
