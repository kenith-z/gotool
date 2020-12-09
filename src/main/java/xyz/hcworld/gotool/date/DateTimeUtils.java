package xyz.hcworld.gotool.date;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
