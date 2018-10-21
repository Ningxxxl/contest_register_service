package cn.tjpuacm.contest_register_service.system.user.dao;

import cn.tjpuacm.contest_register_service.system.user.po.SysUserPO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author ningxy
 * @date 2018-10-20 20:22
 */
@Repository
public interface SysUserRepository {
    int insertUser(SysUserPO userPO);
}
