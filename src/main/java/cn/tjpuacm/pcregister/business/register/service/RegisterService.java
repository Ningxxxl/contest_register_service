package cn.tjpuacm.pcregister.business.register.service;

import cn.tjpuacm.pcregister.system.user.po.SysUserPO;

/**
 * @author ningxy
 * @date 2018-10-22 16:47
 */
public interface RegisterService {
    int activate(SysUserPO userPO);
}
