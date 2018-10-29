package cn.edu.hytc.schoolhelper.common;

import android.support.annotation.IntDef;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2018/10/16.
 */

public class DateUtil {
    static final String[] weeks = new String[] { "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    public static String formatDate(Date date, String  format){
        if(date == null){
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return  df.format(date);
    }
    public static String getDisplayWeek(Date date){
        if(date == null){
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return weeks[week - 1];
    }
}
