package cn.tjpuacm.pcregister.system.user.controller;

import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.system.user.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ningxy
 * @date 2018-10-21 10:26
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询（单个）用户
     *
     * @param userVO userVO 查询条件 SysUserVO
     * @return 查询结果
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public SysUserVO getUser(SysUserVO userVO) throws InstantiationException, IllegalAccessException {
        return sysUserService.getUser(userVO);
    }
}
