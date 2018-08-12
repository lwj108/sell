package com.wechat.utils;

import java.util.Random;

/**
 * 生成唯一随机主键：时间+随机数
 */
public class KeyUtil {

    /**
     * synchronized 在多线程时保证数据安全
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
