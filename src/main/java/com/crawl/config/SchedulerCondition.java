package com.crawl.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/12 17:03
 */
public class SchedulerCondition implements Condition {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerCondition.class);
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        LOGGER.debug("info scheduling.enabled >>>>>>>>>>>>>>>>>>>:{}",context.getEnvironment().getProperty("spring.task.scheduling.enabled"));
        boolean enable = Boolean.valueOf(context.getEnvironment().getProperty("spring.task.scheduling.enabled"));
        return enable;
    }
}