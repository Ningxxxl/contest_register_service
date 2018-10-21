package cn.tjpuacm.pcregister.system.user.service;

import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.vo.SysUserVO;

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

    /**
     * 查询（单个）用户
     *
     * @param userVO 查询条件 SysUserVO
     * @return SysUserVO
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    SysUserVO getUser(SysUserVO userVO) throws IllegalAccessException, InstantiationException;
}
