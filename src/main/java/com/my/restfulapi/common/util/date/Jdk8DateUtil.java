package com.my.restfulapi.common.util.date;

import lombok.NoArgsConstructor;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@NoArgsConstructor
public class Jdk8DateUtil {


    public static final long INTERVAL_UNIT = 1000;

    public static final long MILLISECONDS_SECOND = 1000;
    public static final long MILLISECONDS_MINUTE = 60000;
    public static final long MILLISECONDS_HOUR = 3600000;
    public static final long MILLISECONDS_DAY = 86400000;

    public final static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyyMMdd = "yyyyMMdd";
    public final static String yyyy_MM = "yyyy-MM";
    public final static String yyyyMM = "yyyyMM";
    public final static String yyyy = "yyyy";
    public final static String HHmmss = "HHmmss";
    public final static String HH_mm_ss = "HH:mm:ss";

    /********************************************************
     * Date to String
     *******************************************************/

    /**
     * 转化当前时间为默认格式,默认格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNowDate2String() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 转化当前时间为指定格式
     *
     * @param format
     * @return
     */
    public static String getNowDate2String(String format) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转化指定时间为默认格式,默认格式:yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime
     * @return
     */
    public static String getDate2String(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 转化指定时间为指定格式
     * <p>
     * localDateTime is null, 则返回""
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getDate2String(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /********************************************************
     * String to Date
     *******************************************************/

    /**
     * 转化指定时间为默认格式,默认格式:yyyy-MM-dd HH:mm:ss
     * <p>
     * 如果解析失败,则返回null
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getString2Date(String localDateTime) {
        return getString2Date(localDateTime, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 转化指定时间为指定格式
     * <p>
     * 如果解析失败,则返回null
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static LocalDateTime getString2Date(String localDateTime, String format) {
        try {

            return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(format));
        } catch (RuntimeException e) {
            return null;
        }
    }

    /********************************************************
     * 获取当前的时间值
     *******************************************************/

    /**
     * 获取当前秒数
     *
     * @return
     */
    public static long getSecondTime() {
        return Clock.systemDefaultZone().instant().getEpochSecond();
    }

    /**
     * 获取指定时间的秒数
     *
     * @param localDateTime
     * @return
     */
    public static long getSecondTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取当前时间毫秒数
     *
     * @return
     */
    public static long getMilliSecondsTime() {
        return Clock.systemDefaultZone().millis();
    }

    /**
     * 获取指定时间毫秒数
     *
     * @return
     */
    public static long getMilliSecondsTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /********************************************************
     * 获取开始结束的起始时间 - 天
     *******************************************************/

    /**
     * 获取当前开始时间,默认格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getTodayBegin2String() {
        return getTodayBegin().format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 获取当前结束时间,默认格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getTodayEnd2String() {
        return getTodayEnd().format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 获取当前开始时间
     *
     * @return
     */
    public static LocalDateTime getTodayBegin() {
        return getBegin4Date(LocalDateTime.now());
    }

    /**
     * 获取当前结束时间
     *
     * @return
     */
    public static LocalDateTime getTodayEnd() {
        return getEnd4Date(LocalDateTime.now());
    }

    /**
     * 获取指定时间的开始时间
     *
     * @param localDateTime
     * @return
     */
    public static String getBegin4Date2String(LocalDateTime localDateTime) {
        return getBegin4Date(localDateTime).format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 获取指定时间的结束时间
     *
     * @param localDateTime
     * @return
     */
    public static String getEnd4Date2String(LocalDateTime localDateTime) {
        return getEnd4Date(localDateTime).format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
    }

    /**
     * 获取指定时间的开始时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getBegin4Date(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取指定时间的结束时间
     *
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getEnd4Date(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
    }

    /********************************************************
     * 获取开始结束的起始时间 - 月
     *******************************************************/

    /**
     * 获取当前月初 - yyyy-MM-dd
     *
     * @return
     */
    public static String getMonthBegin2String() {
        return getMonthBegin().format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
    }

    /**
     * 获取当前月末 - yyyy-MM-dd
     *
     * @return
     */
    public static String getMonthEnd2String() {
        return getMonthEnd().format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
    }

    /**
     * 获取当前月初 - yyyy-MM-dd
     *
     * @return
     */
    public static LocalDate getMonthBegin() {
        return getBegin4Month(LocalDate.now());
    }

    /**
     * 获取当前月末 - yyyy-MM-dd
     *
     * @return
     */
    public static LocalDate getMonthEnd() {
        return getEnd4Month(LocalDate.now());
    }

    /**
     * 获取指定日期月初 - yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static String getBegin4Month2String(LocalDate localDate) {
        return getBegin4Month(localDate).format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
    }

    /**
     * 获取指定日期月末 - yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static String getEnd4Month2String(LocalDate localDate) {
        return getEnd4Month(localDate).format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
    }

    /**
     * 获取当前月 - yyyy-MM
     *
     * @return
     */
    public static String getTodayMonth2String() {
        return getMonth2String(LocalDate.now());
    }

    /**
     * 获取指定月 - yyyy-MM
     *
     * @param localDate
     * @return
     */
    public static String getMonth2String(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(yyyy_MM));
    }

    /**
     * 获取指定日期月初 - yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static LocalDate getBegin4Month(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期月末 - yyyy-MM-dd
     *
     * @param localDate
     * @return
     */
    public static LocalDate getEnd4Month(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /********************************************************
     * 获取开始结束的起始时间 - 年
     *******************************************************/

    /**
     * 获取当前年初 - yyyy-MM
     *
     * @return
     */
    public static String getYearBegin2String() {
        return getYearBegin().format(DateTimeFormatter.ofPattern(yyyy_MM));
    }

    /**
     * 获取当前年末 - yyyy-MM
     *
     * @return
     */
    public static String getYearEnd2String() {
        return getYearEnd().format(DateTimeFormatter.ofPattern(yyyy_MM));
    }

    /**
     * 获取当前年 - yyyy
     *
     * @return
     */
    public static String getTodayYear2String() {
        return getYear2String(LocalDate.now());
    }

    /**
     * 获取指定年 - yyyy
     *
     * @param localDate
     * @return
     */
    public static String getYear2String(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(yyyy));
    }

    /**
     * 获取当前年初 - yyyy-MM
     *
     * @return
     */
    public static LocalDate getYearBegin() {
        return getBegin4Year(LocalDate.now());
    }

    /**
     * 获取当前年末 - yyyy-MM
     *
     * @return
     */
    public static LocalDate getYearEnd() {
        return getEnd4Year(LocalDate.now());
    }

    /**
     * 获取指定时间年初 - yyyy-MM
     *
     * @return
     */
    public static String getBegin4Year2String(LocalDate localDate) {
        return getBegin4Year(localDate).format(DateTimeFormatter.ofPattern(yyyy_MM));
    }

    /**
     * 获取指定时间年末 - yyyy-MM
     *
     * @return
     */
    public static String getEnd4Year2String(LocalDate localDate) {
        return getEnd4Year(localDate).format(DateTimeFormatter.ofPattern(yyyy_MM));
    }

    /**
     * 获取指定时间年初 - yyyy-MM
     *
     * @return
     */
    public static LocalDate getBegin4Year(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取指定时间年末 - yyyy-MM
     *
     * @return
     */
    public static LocalDate getEnd4Year(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfYear());
    }

    /********************************************************
     * 时间差
     *******************************************************/

    /**
     * 时间差,单位:天
     *
     * @param fromLocalDate
     * @param toLocalDate
     * @return
     */
    public static int diffDay(LocalDate fromLocalDate, LocalDate toLocalDate) {
        return (int) Duration.between(fromLocalDate, toLocalDate).toDays();
    }

    /**
     * 时间差,单位:小时
     *
     * @param fromLocalTime
     * @param toLocalTime
     * @return
     */
    public static int diffHour(LocalTime fromLocalTime, LocalTime toLocalTime) {
        return (int) Duration.between(fromLocalTime, toLocalTime).toHours();
    }

    /**
     * 时间差,单位:分钟
     *
     * @param fromLocalTime
     * @param toLocalTime
     * @return
     */
    public static int diffMin(LocalTime fromLocalTime, LocalTime toLocalTime) {
        return (int) Duration.between(fromLocalTime, toLocalTime).toMinutes();
    }

    /**
     * 时间差,单位:秒
     *
     * @param fromLocalTime
     * @param toLocalTime
     * @return
     */
    public static int diffSecond(LocalTime fromLocalTime, LocalTime toLocalTime) {
        return (int) ChronoUnit.SECONDS.between(fromLocalTime, toLocalTime);
    }

    /********************************************************
     * 其他使用
     *******************************************************/

    /**
     * 得到指定星期几(星期天返回7)
     *
     * @return
     */
    public static int getDayOfWeekCN(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 按中国习惯，返回今天是星期几(星期天返回7)
     *
     * @return
     */
    public static int getDayOfWeekCN() {
        return getDayOfWeekCN(LocalDate.now());
    }

    /**
     * 得到指定日期是几号
     *
     * @param localDate
     * @return
     */
    public static int getDayOfMonth(LocalDate localDate) {
        return localDate.getDayOfMonth();
    }

    /**
     * 得到今天是几月
     *
     * @return
     */
    public static int getDayOfMonth() {
        return getDayOfMonth(LocalDate.now());
    }

    /**
     * 得到指定日期的月份
     *
     * @param localDate
     * @return
     */
    public static int getMonth(LocalDate localDate) {
        return localDate.getMonth().getValue();
    }

    /**
     * 得到今天是几月
     *
     * @return
     */
    public static int getMonth() {
        return getMonth(LocalDate.now());
    }

    /**
     * 得到指定日期的小时
     *
     * @param localTime
     * @return
     */
    public static int getHour(LocalTime localTime) {
        return localTime.getHour();
    }

    /**
     * 得到指定日期的分钟
     *
     * @param localTime
     * @return
     */
    public static int getMinute(LocalTime localTime) {
        return localTime.getMinute();
    }

    /**
     * 获取指定时间的月份
     *
     * @param localDate
     * @return
     */
    public static String getStringMonth(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MM"));
    }

    /**
     * 获取指定时间的天
     *
     * @param localDate
     * @return
     */
    public static String getStringDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd"));
    }

    /**
     * 获取指定时间的小时
     *
     * @param localTime
     * @return
     */
    public static String getStringHour(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    /**
     * 获取指定时间的分钟
     *
     * @param localTime
     * @return
     */
    public static String getStringMinute(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern("mm"));
    }

    /**
     * 获取指定时间的秒
     *
     * @param localTime
     * @return
     */
    public static String getStringSecond(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern("ss"));
    }

    /********************************************************
     * 扩展
     *******************************************************/

    /**
     * 判断日期是否在范围内，包含相等的日期
     */
    public static boolean isBetween(LocalDateTime localDateTime, LocalDateTime startLocalDateTime,
                                    LocalDateTime endLocalDateTime) {
        if (localDateTime == null || startLocalDateTime == null || endLocalDateTime == null || startLocalDateTime.isAfter(endLocalDateTime)) {
            return false;
        }
        return !localDateTime.isBefore(startLocalDateTime) && !localDateTime.isAfter(endLocalDateTime);
    }

    /********************************************************
     * 转化
     *******************************************************/
    public LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date2Instant(date), ZoneId.systemDefault());
    }

    public Date localDatseTime2Date(LocalDateTime localDateTime) {
        return instant2Date(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date instant2Date(Instant instant) {
        return Date.from(instant);
    }

    public Instant date2Instant(Date date) {
        return date.toInstant();
    }

    public LocalDateTime instant2LocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public Instant LocalDateTime2instant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}
