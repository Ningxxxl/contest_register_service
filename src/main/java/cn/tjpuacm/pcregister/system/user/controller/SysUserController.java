package cn.tjpuacm.pcregister.system.user.controller;

import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.system.user.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ningxy
 * @date 2018-10-21 10:26
 */
@RestController
@RequestMapping(value = "/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    /**
     * 查询（单个）用户
     *
     * @param userVO userVO 查询条件 SysUserVO
     * @return 查询结果
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public SysUserVO getUser(SysUserVO userVO) throws InstantiationException, IllegalAccessException {
        return sysUserService.getUser(userVO);
    }
}
