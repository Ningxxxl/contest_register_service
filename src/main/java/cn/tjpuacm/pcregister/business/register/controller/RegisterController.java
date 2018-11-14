package cn.tjpuacm.pcregister.business.register.controller;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.entity.ResultBody;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ningxy
 * @date 2018-10-21 19:46
 */
@Api(tags = "注册服务")
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private SysUserService sysUserService;

    @Value("${jwt.properties.emailActivateExpiration}")
    private long expiration;

    @ApiOperation(value = "获取验证码", notes = "短信验证码，频次默认1条/分钟，3条/天")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultBody register(@RequestBody String userInfoJsonStr) throws GlobalErrorException {
        return ResultBody.generateSuccessResult(registerService.generateActivationCode(userInfoJsonStr));
    }

    @ApiOperation(value = "正式注册", notes = "验证验证码，完成注册")
    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public ResultBody activate(@RequestBody String userInfoJsonStr) throws GlobalErrorException, InstantiationException, IllegalAccessException {
        return ResultBody.generateSuccessResult(registerService.activate(userInfoJsonStr) == 1);
    }
}
