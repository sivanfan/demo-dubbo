package com.ule.demo.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Author fanxl
 * @Description 时间工具类
 * @Date 15:32  2019/1/28
 * @Version 1.0
 **/
@Slf4j
public class DateUtil {

    public final static String CEN_YYYYMMDD = "yyyy/MM/dd";
    public final static String DATAFORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String CN_YYYY_MM_DD = "yyyy年MM月dd日";
    public final static String DATAFORMAT_YYYYMMDD = "yyyyMMdd";
    public final static String DATAFORMAT_YYYY_MM_DDHHmm = "yyyy-MM-dd HH:mm";
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_SSS_FORMAT = "yyyyMMddHHmmSSS";
    public final static String DATEFORMAT_yyyy = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期字符串转日期
     * @param dateString
     * @param dateString pattern
     * @return
     */
    public static Date strToDate(String dateString, String pattern){
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
            return null;
        }
    }

    public static Date getTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 将传入日期格式化成指定格式。
     *
     * @param d
     *            要格式化的日期
     * @param pattern
     *            格式化的样式
     *
     * @return 日期的字符串形式
     */
    public static String format(Date d, String pattern) {
        if (d == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(d);
    }

    public static Date getCommStyleTime(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return df.parse(date);
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
        }
        return null;
    }

    public static Date getCommStyleTime(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(date);
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
        }
        return null;
    }

    /**
     * 两个日期相减得天数
     *
     * @param enddate
     *            结束日期 date类型
     * @param begindate
     *            开始日期 date类型
     * @return 天数
     */
    public static int getIntervalDays(Date enddate, Date begindate) {
        long millisecond = enddate.getTime() - begindate.getTime();
        int day = (int) (millisecond / 24L / 60L / 60L / 1000L);
        return day;
    }

    /**
     * 两个日期相减得天数
     *
     * @param begindate
     *            开始日期 (减数)
     * @param enddate
     *            结束日期 (被减数)
     * @param formatString
     *            日期格式
     * @return 天数
     * @throws ParseException
     */
    public static int getIntervalDays(String begindate, String enddate, String formatString) throws ParseException {
        int oneday = 1000 * 60 * 60 * 24;
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTimeInMillis(stringToDateLong(begindate, formatString));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(stringToDateLong(enddate, formatString));
        int interval = (int) ((endCalendar.getTimeInMillis() - beginCalendar.getTimeInMillis()) / oneday);
        return interval;
    }

    private static long stringToDateLong(String adate, String formatString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        return formatter.parse(adate).getTime();
    }

    /**
     * 修正天数 正数向后添加天数，负数减去相应天数
     *
     * @date 2013-7-19 上午10:42:38
     * @param date 日期
     * @param days
     * @return description:
     */
    public static Date getReviseDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 获取指定时间的前一天日期
     *
     * @param theDay
     * @return
     */
    public static String getBeforeTheDay(Date theDay, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Calendar cal = Calendar.getInstance();
        cal.setTime(theDay);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(cal.getTime());
    }
    /**
     * 取得在指定时间上加减days天后的时间
     *
     * @param date
     *            指定的时间
     * @param days
     *            天数,正为加，负为减
     * @return 在指定时间上加减days天后的时间
     */
    public static Date addDays(Date date, int days) {
        Date time = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        time = cal.getTime();
        return time;
    }

    /**
     * 获取指定时间的前一天日期
     * @param dateStr
     * @return
     */
    public static String getBeforeDay(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(cal.getTime());
    }
    /**
     * 获取当前时间的前一天
     * @return
     */
    public static String getBeforeDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return sdf.format(cal.getTime());
    }

    /**
     * 返回日期的天
     * @param date
     * @return
     */
    public static int getDayOfDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @param formatPattern
     * @return
     */
    public static String getDateFormatString(Date date, String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        return format.format(date);
    }

    /**
     * 日期转换成指定格式的日期
     *
     * @param date
     * @return
     */
    public static Date getDateFormatDate(Date date){
        String dateStr=DateUtil.getDateFormatString(date,CEN_YYYYMMDD);
        SimpleDateFormat sdf = new SimpleDateFormat(CEN_YYYYMMDD);
        Date dateNew=null;
        try {
            dateNew=sdf.parse(dateStr);
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
        }
        return dateNew;
    }

    /**
     *  获取两个日期相差的月数
     * @param d1    较大的日期
     * @param d2    较小的日期
     * @return  如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if(month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval --;
        }
        // 获取月数差值
        int monthInterval =  (month1 + 12) - month2  ;
        if(day1 < day2) {
            monthInterval --;
        }
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    /**
     * 获取指定时间的月份的第一天  返回指定日期格式的字符串
     * @param date
     * @author jinzg
     * @return
     */
    public static String getFirstDayOfTheMonth(Date date,String pattern){
        Calendar cal =Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date startDate = cal.getTime();
        return DateUtil.format(startDate, pattern);
    }

    /**
     * 获取指定月份的天数
     * @param date
     * @return
     */
    public static int getMonthLastDay(Date date)
    {
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    /**
     * 根据当前时间加多少分中、多少秒
     * @param beginDate
     * @return
     */
    public static String getCurrentTimeAddMinutes(String beginDate){
        String time = "";
        SimpleDateFormat df= new SimpleDateFormat(DATEFORMAT_yyyy);
        Date date;
        try {
            date = df.parse(beginDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE,-1);
            time = df.format(c.getTime());
        } catch (ParseException e) {
            log.error("======DateUtil======",e);
            return "";
        }

        return time;
    }

    /**
     * 判断日期是否在今天及之前
     * @param date
     * @return
     */
    public static boolean beforeNow(Date date){
        Date nowDate = strToDate(format(new Date(), DATAFORMAT_YYYY_MM_DD), DATAFORMAT_YYYY_MM_DD);
        return (null != date && getIntervalDays(date, nowDate)<=0);
    }

    /**
     * 判断日期是否在今天(+num)及之前
     * @param date
     * @param num
     * @return
     */
    public static boolean beforeNowAddNum(Date date,int num){
        Date nowDate = strToDate(format(new Date(), DATAFORMAT_YYYY_MM_DD), DATAFORMAT_YYYY_MM_DD);
        return (null != date && getIntervalDays(date, DateUtil.addDays(nowDate,num))<=0);
    }
    /**
     *  获取两个日期相差的月数 和day无关
     * @param d1    较大的日期
     * @param d2    较小的日期
     * @return  如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiffNoDate(Date d1, Date d2) {
        int c=0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if(c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        }
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int yearInterval = year1 - year2;
        if(yearInterval>=1){
            //跨年
            c=	(yearInterval*12)+c1.get(Calendar.MONDAY) - c2.get(Calendar.MONTH)+1;
        }else{
            c=	c1.get(Calendar.MONDAY) - c2.get(Calendar.MONTH)+1;
        }
        return c;
    }
}
