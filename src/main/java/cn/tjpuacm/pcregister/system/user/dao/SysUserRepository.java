package cn.tjpuacm.pcregister.system.user.dao;

import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import org.springframework.stereotype.Repository;

/**
 * @author ningxy
 * @date 2018-10-20 20:22
 */
@Repository
public interface SysUserRepository {
    /**
     * 插入（新增）用户
     *
     * @param userPO SysUserPO
     * @return rows
     */
    int insertUser(SysUserPO userPO);
}
