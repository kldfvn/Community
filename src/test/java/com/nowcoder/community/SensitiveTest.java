package com.nowcoder.community;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
//配置类
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTest {
    @Autowired
    SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text="这里可以赌博,可以吸毒，可以嫖娼！！！";
        System.out.println(sensitiveFilter.filter(text));
    }
}
