package cn.tjpuacm.pcregister.system.user.service;

import cn.tjpuacm.pcregister.system.user.po.SysUserPO;

/**
 * @author ningxy
 * @date 2018-10-20 21:11
 */
public interface SysUserService {
    /**
     * 插入（新增）用户
     *
     * @param userPO SysUserPO
     * @return rows
     */
    int insertUser(SysUserPO userPO);
}
