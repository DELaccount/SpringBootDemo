package com.qiyuesuo;

import com.qiyuesuo.config.ApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilesysApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FilesysApplication.class);
        springApplication.addListeners(new ApplicationStartedEventListener());//添加监听器
        springApplication.run(args);//启动服务
    }
}
