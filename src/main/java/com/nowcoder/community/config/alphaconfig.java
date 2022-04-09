package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//配置类
@Configuration
public class alphaconfig {
    //BEAN 注解
    @Bean
    public SimpleDateFormat simpleDateFormat()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
