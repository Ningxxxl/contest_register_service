package cn.tjpuacm.pcregister.util;

import org.springframework.beans.BeanUtils;

/**
 * @author ningxy
 * 转换工具类
 * @date 2018-10-16 00:10
 */
public class TransformerUtil {
    /**
     * 转换PO为VO
     *
     * @param clazz VO的class
     * @param po    po对象
     * @param <T>   VO的class
     * @return 转化后的vo
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T po2VO(Class<T> clazz, Object po) throws IllegalAccessException, InstantiationException {
        if (po == null) {
            return null;
        }
        T vo = clazz.newInstance();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    /**
     * 转换PO为VO
     *
     * @param clazz PO的class
     * @param vo    vo对象
     * @param <T>   PO的class
     * @return 转化后的po
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T vo2PO(Class<T> clazz, Object vo) throws IllegalAccessException, InstantiationException {
        if (vo == null) {
            return null;
        }
        T po = clazz.newInstance();
        BeanUtils.copyProperties(vo, po);
        return po;
    }
}
