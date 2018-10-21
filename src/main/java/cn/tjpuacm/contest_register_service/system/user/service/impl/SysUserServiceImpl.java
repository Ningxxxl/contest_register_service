package cn.tjpuacm.contest_register_service.system.user.service.impl;

import cn.tjpuacm.contest_register_service.system.user.dao.SysUserRepository;
import cn.tjpuacm.contest_register_service.system.user.po.SysUserPO;
import cn.tjpuacm.contest_register_service.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ningxy
 * @date 2018-10-20 21:13
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public int insertUser(SysUserPO userPO) {
        return sysUserRepository.insertUser(userPO);
    }
}
