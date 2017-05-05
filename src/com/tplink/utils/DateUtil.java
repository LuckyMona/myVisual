/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * DateUtil.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2016-7-29, TangWeicheng, Create file
 */

package com.tplink.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class DateUtil.
 */
public class DateUtil {

    public static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;

    public static final long MILLISECONDS_PER_SECOND = 1000;

    /**
     * Format date.
     *
     * @param date the date
     * @param formatStr the format str
     * @return the string
     */
    // 按照给定的格式化字符串格式化日期
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    /**
     * Parses the date.
     *
     * @param dateStr the date str
     * @param formatStr the format str
     * @return the date
     * @throws ParseException the parse exception
     */
    // 按照给定的格式化字符串解析日期
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 判断给定日期是否为月初的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断给定日期是否为月末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Parses the date.
     *
     * @param dateStr the date str
     * @return the date
     * @throws ParseException the parse exception
     */
    // 从字符串中分析日期
    public static Date parseDate(String dateStr) throws ParseException {
        Date date = null;
        String[] dateArray = dateStr.split("\\D+"); // +防止多个非数字字符在一起时导致解析错误
        int dateLen = dateArray.length;
        int dateStrLen = dateStr.length();
        if (dateLen > 0) {
            if ((dateLen == 1) && (dateStrLen > 4)) {
                if (dateStrLen == "yyyyMMddHHmmss".length()) {
                    // 如果字符串长度为14位并且不包含其他非数字字符，则按照（yyyyMMddHHmmss）格式解析
                    date = parseDate(dateStr, "yyyyMMddHHmmss");
                } else if (dateStrLen == "yyyyMMddHHmm".length()) {
                    date = parseDate(dateStr, "yyyyMMddHHmm");
                } else if (dateStrLen == "yyyyMMddHH".length()) {
                    date = parseDate(dateStr, "yyyyMMddHH");
                } else if (dateStrLen == "yyyyMMdd".length()) {
                    date = parseDate(dateStr, "yyyyMMdd");
                } else if (dateStrLen == "yyyyMM".length()) {
                    date = parseDate(dateStr, "yyyyMM");
                }
            } else {
                String fDateStr = dateArray[0];
                for (int i = 1; i < dateLen; i++) {
                    // 左补齐是防止十位数省略的情况
                    fDateStr += leftPad(dateArray[i], "0", 2);
                }

                if (dateStr.trim().matches("^\\d{1,2}:\\d{1,2}(:\\d{1,2})?$")) {
                    // 补充年月日3个字段
                    dateLen += 3;
                    fDateStr = formatDate(new Date(), "yyyyMMdd") + fDateStr;
                }

                date = parseDate(fDateStr, "yyyyMMddHHmmss".substring(0, ((dateLen - 1) * 2) + 4));
            }
        }

        return date;
    }

    /**
     * Left pad.
     *
     * @param str the str
     * @param pad the pad
     * @param len the len
     * @return the string
     */
    // 左补齐
    public static String leftPad(String str, String pad, int len) {
        String newStr = (str == null ? "" : str);
        while (newStr.length() < len) {
            newStr = pad + newStr;
        }
        if (newStr.length() > len) {
            newStr = newStr.substring(newStr.length() - len);
        }
        return newStr;
    }

    public static int getDays(Date start, Date end) {

        long diff = Math.abs(start.getTime() - end.getTime());

        return (int)(diff / MILLISECONDS_PER_DAY);
    }

    public static Date getFirstDayOfMonth(Date date) {
        /** 去掉尾数，获取当天的0点 */
        long da = date.getTime() - (date.getTime() % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);

        return new Date(cal.getTimeInMillis());
    }

    public static long getFirstDayOfMonth(long date) {
        /** 去掉尾数，获取当天的0点 */
        long da = date - (date % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);

        return cal.getTimeInMillis();
    }

    public static Date getFirstDayOfNextMonth(Date date) {

        long da = date.getTime() - (date.getTime() % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);
        cal.add(Calendar.MONTH, 1);

        return new Date(cal.getTimeInMillis());
    }

    public static long getFirstDayOfNextMonth(long date) {

        long da = date - (date % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);
        cal.add(Calendar.MONTH, 1);

        return cal.getTimeInMillis();
    }

    public static long getLastDayOfMonth(long date) {

        long da = date - (date % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        return cal.getTimeInMillis();
    }

    public static Date getLastDayOfMonth(Date date) {

        long da = date.getTime() - (date.getTime() % MILLISECONDS_PER_DAY);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(da);

        int dayofMonth = cal.get(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayofMonth);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);

        return new Date(cal.getTimeInMillis());
    }

    public static Date getLastTimeOfToday(Date date) {

        long mi = date.getTime();

        long newmi = (mi - (mi % (DateUtil.MILLISECONDS_PER_DAY)) - MILLISECONDS_PER_SECOND)
                + MILLISECONDS_PER_DAY;

        return new Date(newmi);
    }

    public static Date getLastTimeOfYesterday(Date date) {
        long mi = date.getTime();

        long newmi = mi - (mi % (DateUtil.MILLISECONDS_PER_DAY)) - MILLISECONDS_PER_SECOND;

        return new Date(newmi);
    }

    public static Date getFirstTimeOfToday(Date date) {
        long mi = date.getTime();

        long newmi = mi - (mi % (DateUtil.MILLISECONDS_PER_DAY));

        return new Date(newmi);
    }

    public static long getFirstTimeOfToday(long date) {

        long newmi = date - (date % (DateUtil.MILLISECONDS_PER_DAY));

        return newmi;
    }

    public static Date getFirstTimeOfTomorrow(Date date) {
        long mi = date.getTime();

        long newmi = (mi - (mi % (DateUtil.MILLISECONDS_PER_DAY))) + DateUtil.MILLISECONDS_PER_DAY;

        return new Date(newmi);
    }

    public static long getFirstTimeOfTomorrow(long date) {

        long newmi = (date - (date % (DateUtil.MILLISECONDS_PER_DAY)))
                + DateUtil.MILLISECONDS_PER_DAY;

        return newmi;
    }

    public static Date getFirstTimeOfYesterday(Date date) {
        long mi = date.getTime();

        long newmi = (mi - (mi % (DateUtil.MILLISECONDS_PER_DAY))) - DateUtil.MILLISECONDS_PER_DAY;

        return new Date(newmi);
    }

    /**
     * 获取当前日期，时间为0点（0时区时间）。
     *
     * @return the current date
     */
    public static Date getCurrentDate() {
        Date date = new Date();

        long mi = date.getTime();

        long newmi = mi - (mi % (DateUtil.MILLISECONDS_PER_DAY));

        return new Date(newmi);
    }

    /** 获取下一天0点的日期 */
    public static Date getNextDate() {
        Date date = new Date();

        long mi = date.getTime();

        long newmi = (mi - (mi % (DateUtil.MILLISECONDS_PER_DAY))) + DateUtil.MILLISECONDS_PER_DAY;

        return new Date(newmi);
    }

    public static Date getLastWeekBefore(Date date) {
        long mi = date.getTime();
        long newmi = mi - (7 * (DateUtil.MILLISECONDS_PER_DAY));
        return new Date(newmi);
    }

    /** 获取明天的这个时刻 */
    public static Date getThisTimeOfTomorrow(Date t) {

        long mi = t.getTime();

        long newmi = mi + DateUtil.MILLISECONDS_PER_DAY;

        return new Date(newmi);
    }

}
