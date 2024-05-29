package com.xjl.eimui.util;

import android.content.Context;
import android.util.Log;

import com.xjl.eimui.EIMUI;
import com.xjl.eimui.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DadaFormatUtil {


    public static String getTimeInterval(String time) {
        if (isNumber(time)) {
            return getTimeInterval(Long.parseLong(time));
        }
        return time;
    }

    public static boolean isNumber(String str) {
        if (str.length() > 0 && EIMUI.INSTANCE.getContext() != null) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        }
        return false;
    }


    public static String getTimeInterval(long time) {

        long interval = System.currentTimeMillis() - time;//获取时间差

        long fiveMin = 5 * 60 * 1000;//5分钟的时间 300,000
        /*if(interval<fiveMin){
            return "刚刚";
        }*/
        long oneHour = 60 * 60 * 1000;//1个小时的时间 3,600,000
        if (interval < oneHour) {
            long min = interval / (60 * 1000);
            return min + " " + EIMUI.INSTANCE.getContext().getString(R.string.minute_before);
        }
        long zeroToNow = (getCurHour() * 60 + getCurMINUTE()) * 60 * 1000;//获取0点到当前时分的时间
        if (interval < zeroToNow) {//如果时间搓小于0点到当前的时分，则是今天
            long hour = interval / oneHour;
            return hour + EIMUI.INSTANCE.getContext().getString(R.string.hour_before);
        }

        long oneDay = 24 * oneHour;

        long yesZeroToNow = oneDay + zeroToNow;//获取昨天0点到当前时分的时间
        if (interval < yesZeroToNow) {//如果时间小于昨天0点到现在的时分，则是昨天
            return EIMUI.INSTANCE.getContext().getString(R.string.yesterday) + getStringTime(time, "HH:mm");
        }
        //大于的情况下，昨天以前的时间
        return getStringTime(time, "MM:dd hh:mm");
    }

    /**
     * 获取当前Year
     *
     * @return
     */
    public static int getCurYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前MONTH
     *
     * @return
     */
    public static int getCurMONTH() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前Day
     *
     * @return
     */
    public static int getCurDAY() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前Hour
     *
     * @return
     */
    public static int getCurHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前MINUTE
     *
     * @return
     */
    public static int getCurMINUTE() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);

    }

    public static String getStringTime(long time, String format) {
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getStringTime(calendar, format);
    }

    public static String getStringTime(Calendar c, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(c.getTimeInMillis());
        return formatter.format(curDate);
    }

}
