package com.wechat.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
//@Data
public class LoggerTest {

    /**
     * 用@Slf4j代替，简化书写LoggerTest.clas指定类
     */
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name ="lwj";
        String password ="123456";
        logger.debug("debug...");
        logger.info("name: {}, password: {}", name, password);
        logger.error("error...");
    }
}
