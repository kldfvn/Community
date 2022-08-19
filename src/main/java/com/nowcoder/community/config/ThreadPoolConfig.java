package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//线程池定时任务配置
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
