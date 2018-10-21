package cn.tjpuacm.contest_register_service.system.user.service;

import cn.tjpuacm.contest_register_service.system.user.po.SysUserPO;
import org.springframework.stereotype.Service;

/**
 * @author ningxy
 * @date 2018-10-20 21:11
 */
public interface SysUserService {
    int insertUser(SysUserPO userPO);
}
