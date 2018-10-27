package cn.tjpuacm.pcregister.business.register.service;

import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;

/**
 * @author ningxy
 * @date 2018-10-22 16:47
 */
public interface RegisterService {
    /**
     * 生成激活码
     * @param phone 手机号
     * @param studentId 学号
     * @return 激活码
     */
    String generateActivationCode(String phone, String studentId) throws GlobalErrorException;

    /**
     * 激活用户
     *
     * @param userPO SysUserPO
     * @return rows
     */
    int activate(SysUserPO userPO);
}
