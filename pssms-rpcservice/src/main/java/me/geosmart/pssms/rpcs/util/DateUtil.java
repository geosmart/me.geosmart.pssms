package me.geosmart.pssms.rpcs.util;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 日期辅助类
 */
public class DateUtil {
    private final static Logger logger = Logger.getLogger(DateUtil.class);
    static SnowFlake snowFlake = null;

    public static Date getDate(String dateString) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = bartDateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * 格式化时间
     *
     * @param time   时间
     * @param format 时间格式
     * @return 时间字串
     */
    public static String formatDateTime(java.util.Date time, String format) {
        if (time == null)
            return "";
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(time);
    }

}
