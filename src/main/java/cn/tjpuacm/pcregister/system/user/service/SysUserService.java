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

    /**
     * 根据学号查找验证码
     *
     * @param studentId 学号
     * @return 验证码
     */
    String getActivationCodeByStudentId(String studentId);

    /**
     * 更新用户信息
     *
     * @param userPO SysUserPO
     * @return rows
     */
    int updateUserByStudentId(SysUserPO userPO);
}
