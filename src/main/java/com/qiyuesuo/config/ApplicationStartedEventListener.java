package com.qiyuesuo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 启动时创建文件根目录
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartingEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        File file = new File("./File");
        if (!file.exists()) {
            file.mkdirs();
            logger.info("创建文件上传目录");
        }
    }
}