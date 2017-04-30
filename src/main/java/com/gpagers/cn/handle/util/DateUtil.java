package com.gpagers.cn.handle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fred on 16/9/7.
 */
public class DateUtil {

    public static String getNowStr(){
        return getDateStr(new Date());
    }


    public static String getDateStrAfter(String date, int day)throws ParseException {
        return getStrDay(getDateBefore(getDateFromStr(date),day*-1));
    }
    /**
     * 获取时间字符串
     * @return
     */
    public static String getDateStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }


    /**
     * 获取时间对象
     * @return
     */
    public static Date getDateFromStr(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static Date getDateFromStrNoSpilt(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(date);
    }

    public static Date getDateTimeFromStr(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public static String getStrDay(Date date){
        if(date==null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getStrDayBefore(Date date, int day){
        if(date==null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(getDateBefore(date,day));
    }

    public static String getStrDayNoSpilt(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    public static Date getDateOf2359(String time) throws ParseException {
       return getDateOf2359(getDateFromStr(time));
    }

    public static Date getDateOf0000(String time) throws ParseException {
        return getDateOf0000(getDateFromStr(time));
    }
    public static Date getDateOf0000(long time) throws ParseException {
        return getDateOf0000(new Date(time));
    }

    public static Date getDateOf2359(Date time) {
        if (time == null) {
            time = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getDateOf0000(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     * @param beginDate
     * @param endDate
     * @return List<Date>
     */
    @SuppressWarnings("unchecked")
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List lDate = new ArrayList();
        lDate.add(beginDate);//把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        //使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            //根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                if(!lDate.contains(cal.getTime())){
                    cal.set(Calendar.HOUR_OF_DAY,0);
                    cal.set(Calendar.MINUTE,0);
                    cal.set(Calendar.SECOND,0);
                    lDate.add(cal.getTime());
                }
            } else {
                break;
            }
        }
        if(!lDate.contains(endDate)) {
            lDate.add(endDate);//把结束时间加入集合
        }
        return lDate;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     * @param beginDate
     * @param endDate
     * @return List<Date>
     */
    @SuppressWarnings("unchecked")
    public static List<String> getDateStringBetweenTwoDate(Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List lDate = new ArrayList();
        lDate.add(sdf.format(beginDate));//把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        //使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;

        while (bContinue) {
            //根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                if (!lDate.contains(cal.getTime())) {
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    lDate.add(sdf.format(cal.getTime()));
                }
            } else {
                break;
            }
        }
        return lDate;
    }

}
