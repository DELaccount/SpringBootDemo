package com.qiyuesuo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Auth0r : fangbofei
 * Date : 2018/8/2/0002
 * Time : 9:51
 */
public class TimeUtil {
    /**
     * 获得当前时间
     * @return String 当前时间
     */
    public static String getNowTime() {
        Date now = new Date();
        //按照要求格式获得现在时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = dateFormat.format(now);
        return nowTime;
    }
}
