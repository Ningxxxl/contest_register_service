package cn.tjpuacm.pcregister.util;

import java.util.UUID;

/**
 * @author ningxy
 * UUID 工具类
 * @date 2018-10-15 20:50
 */
public class UUIDUtils {
    /**
     * 生成32位没有'-'分割的UUID字符串
     * @return 32位没有'-'分割的UUID字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
