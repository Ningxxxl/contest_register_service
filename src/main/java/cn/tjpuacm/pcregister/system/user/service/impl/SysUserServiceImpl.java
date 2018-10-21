package cn.tjpuacm.pcregister.system.user.service.impl;

import cn.tjpuacm.pcregister.system.user.dao.SysUserRepository;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.system.user.vo.SysUserVO;
import cn.tjpuacm.pcregister.util.TransformerUtil;
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

    /**
     * 查询（单个）用户
     *
     * @param userVO 查询条件 SysUserVO
     * @return SysUserVO
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public SysUserVO getUser(SysUserVO userVO) throws IllegalAccessException, InstantiationException {
        final SysUserPO userPO = sysUserRepository.getUser(userVO);
        final SysUserVO resUserVO = TransformerUtil.po2VO(SysUserVO.class, userPO);
        return resUserVO;
    }
}