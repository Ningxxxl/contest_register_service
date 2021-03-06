package cn.tjpuacm.pcregister.business.register.service;

import cn.tjpuacm.pcregister.exception.GlobalErrorException;

/**
 * @author ningxy
 * @date 2018-10-22 16:47
 */
public interface RegisterService {
    /**
     * 生成激活码
     * @param userInfoStr 手机号和学号信息 json字符串
     * @return 激活码
     * @throws GlobalErrorException
     */
    String generateActivationCode(String userInfoStr) throws GlobalErrorException;

    /**
     * 激活用户
     *
     * @param userInfoJsonStr 用户信息
     * @return rows
     * @throws GlobalErrorException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    int activate(String userInfoJsonStr) throws GlobalErrorException, IllegalAccessException, InstantiationException;
}
