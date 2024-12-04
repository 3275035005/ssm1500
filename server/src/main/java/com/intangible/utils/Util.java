package com.intangible.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:yyyy-mm-dd hh:mm:ss
     *
     *
     * @return String 返回当前字符串型日期时间
     */
    public static String getTime() {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }
}
