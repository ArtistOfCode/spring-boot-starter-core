package com.codeartist.component.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 日期时间工具类
 *
 * @author AiJiangnan
 * @date 2020/10/9
 * @see LocalDateTime
 * @see LocalDate
 * @see LocalTime
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    public static final String[] DAY_OF_WEEK = {"", "一", "二", "三", "四", "五", "六", "日"};

    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LONG_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LONG_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String format(TemporalAccessor dateTime) {
        return LONG_DATETIME_FORMATTER.format(dateTime);
    }

    public static String formatDate(TemporalAccessor date) {
        return LONG_DATE_FORMATTER.format(date);
    }

    public static String formatTime(TemporalAccessor time) {
        return LONG_TIME_FORMATTER.format(time);
    }

    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.from(LONG_DATETIME_FORMATTER.parse(dateTime));
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.from(LONG_DATE_FORMATTER.parse(date));
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.from(LONG_TIME_FORMATTER.parse(time));
    }
}
