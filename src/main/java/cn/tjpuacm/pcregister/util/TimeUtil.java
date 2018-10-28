package cn.tjpuacm.pcregister.util;

import java.util.Calendar;

/**
 * @author ningxy
 * @date 2018-10-28 21:49
 */
public class TimeUtil {
    /**
     * 获取当前时间到第二天凌晨的毫秒
     * @return
     */
    public static long getMillisNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis());
    }
}
