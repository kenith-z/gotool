package xyz.hcworld.gotool.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具包
 *
 * @ClassName: DateTimeUtils
 * @Author: 张红尘
 * @Date: 2019/11/18 11:21
 * @Version： 1.0
 */
public class DateTimeUtils {
    private static String ZONE = "+8";
    private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter TIMES = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置设置时区["-18","+18"}
     *
     * @param zone 时区
     */
    public static void setZone(String zone) {
        ZONE=zone;
    }

    /**
     * 年月日<br>
     * yyyy-MM-dd
     */
    public static String getCurrentDateStr() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).format(DATE);
    }
    /**
     * 年月日 时分秒<br>
     * yyyy-MM-dd
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(ZoneOffset.of(ZONE));
    }
    /**
     * 年月日<br>
     * yyyy-MM-dd
     */
    public static LocalDate getCurrentDate() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).toLocalDate();
    }
    /**
     * 时分秒<br>
     * yyyy-MM-dd
     */
    public static LocalTime getCurrentTime() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).toLocalTime();
    }

    public static Instant getCurrentInstant() {
        return Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
    }


    /**
     * LocalDateTime转Date<br>
     * @param dateTime LocalDateTime类
     * @return Date类
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneOffset.of(ZONE)).toInstant());
    }
    /**
     * Date转LocalDateTime<br>
     * @param date Date
     * @return LocalDateTime类
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneOffset.of(ZONE)).toLocalDateTime();
    }

    /**
     * 时分秒<br>
     * HH:mm:ss
     */
    public static String getCurrentTimeStr() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).format(TIME);
    }
    /**
     * 时分秒毫秒<br>
     * HH:mm:ss:SSS
     */
    public static String getCurrentTimeSssStr() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).format(TIMES);
    }
    /**
     * 年月日 时分秒<br>
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now(ZoneOffset.of(ZONE)).format(DATE_TIME);
    }
}
