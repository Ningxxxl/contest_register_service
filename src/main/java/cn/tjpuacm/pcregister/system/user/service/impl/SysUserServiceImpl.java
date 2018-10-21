package cn.tjpuacm.pcregister.system.user.service.impl;

import cn.tjpuacm.pcregister.system.user.dao.SysUserRepository;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
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

    /**
     * 插入（新增）用户
     *
     * @param userPO SysUserPO
     * @return rows
     */
    @Override
    public int insertUser(SysUserPO userPO) {
        return sysUserRepository.insertUser(userPO);
    }
}
