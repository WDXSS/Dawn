package com.dawndemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zc on 2017/5/15.
 */

public class DateUtil {

    private static final String formatType1 = "yyyy-MM-dd HH:mm:ss";
    /* 获取现在时间
       *
       * @return返回字符串格式  yyyy年MM月dd日 HH时mm分
       */
    public static String longToString(Long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat(formatType1);
        return formatter.format(date);
    }
}
