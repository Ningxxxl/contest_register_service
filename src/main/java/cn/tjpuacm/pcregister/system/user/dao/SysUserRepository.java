package cn.tjpuacm.pcregister.system.user.dao;

import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.vo.SysUserVO;
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

    /**
     * 查询（单个）用户
     *
     * @param userVO 查询条件 SysUserVO
     * @return 查询到的用户 SysUserPO
     */
    SysUserPO getUser(SysUserVO userVO);

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
