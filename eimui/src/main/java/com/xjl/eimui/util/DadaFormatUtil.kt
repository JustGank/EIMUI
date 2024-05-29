package com.xjl.eimui.util

import android.text.TextUtils
import com.xjl.eimui.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.regex.Pattern

object DadaFormatUtil {
    // 该部分可以动态设置显示语言内容
    var minute_before: String = "min before"
    var hour_before: String = "hour(s) before"
    var yesterday: String = "Yesterday"
    fun getTimeInterval(time: String): String {
        return if (isNumber(time)) {
            getTimeInterval(time.toLong())
        } else time
    }

    fun isNumber(str: String?): Boolean {
        if (!TextUtils.isEmpty(str)) {
            val pattern = Pattern.compile("[0-9]*")
            val isNum = pattern.matcher(str)
            return isNum.matches()
        }
        return false
    }

    fun getTimeInterval(time: Long): String {
        val interval = System.currentTimeMillis() - time //获取时间差
        val fiveMin = (5 * 60 * 1000).toLong() //5分钟的时间 300,000
        /*if(interval<fiveMin){
            return "刚刚";
        }*/
        val oneHour = (60 * 60 * 1000).toLong() //1个小时的时间 3,600,000
        if (interval < oneHour) {
            return "${interval / (60 * 1000)} $minute_before"
        }
        val zeroToNow = ((curHour * 60 + curMINUTE) * 60 * 1000).toLong() //获取0点到当前时分的时间
        if (interval < zeroToNow) { //如果时间搓小于0点到当前的时分，则是今天
            return "${interval / oneHour} $hour_before"
        }
        val oneDay = 24 * oneHour
        val yesZeroToNow = oneDay + zeroToNow //获取昨天0点到当前时分的时间
        return if (interval < yesZeroToNow) { //如果时间小于昨天0点到现在的时分，则是昨天
            yesterday + getStringTime(time, "HH:mm")
        } else getStringTime(time, "MM:dd hh:mm")
    }

    val curYear: Int
        /**
         * 获取当前Year
         *
         * @return
         */
        get() {
            val c = Calendar.getInstance()
            return c[Calendar.YEAR]
        }
    val curMONTH: Int
        /**
         * 获取当前MONTH
         *
         * @return
         */
        get() {
            val c = Calendar.getInstance()
            return c[Calendar.MONTH] + 1
        }
    val curDAY: Int
        /**
         * 获取当前Day
         *
         * @return
         */
        get() {
            val c = Calendar.getInstance()
            return c[Calendar.DAY_OF_MONTH]
        }
    val curHour: Int
        /**
         * 获取当前Hour
         *
         * @return
         */
        get() {
            val c = Calendar.getInstance()
            return c[Calendar.HOUR_OF_DAY]
        }
    val curMINUTE: Int
        /**
         * 获取当前MINUTE
         *
         * @return
         */
        get() {
            val c = Calendar.getInstance()
            return c[Calendar.MINUTE]
        }

    fun getStringTime(time: Long, format: String?): String {
        val date = Date(time)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return getStringTime(calendar, format)
    }

    fun getStringTime(c: Calendar, format: String?): String {
        val formatter = SimpleDateFormat(format)
        val curDate = Date(c.timeInMillis)
        return formatter.format(curDate)
    }
}
