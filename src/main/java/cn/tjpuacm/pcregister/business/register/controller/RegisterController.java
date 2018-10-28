package cn.tjpuacm.pcregister.business.register.controller;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.entity.ResultBody;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author ningxy
 * @date 2018-10-21 19:46
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private SysUserService sysUserService;

    @Value("${jwt.properties.emailActivateExpiration}")
    private long expiration;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultBody register(@RequestBody String userInfoJsonStr) throws GlobalErrorException {
        return ResultBody.generateSuccessResult(registerService.generateActivationCode(userInfoJsonStr));
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public ResultBody activate(@RequestBody String userInfoJsonStr) throws GlobalErrorException {
        return ResultBody.generateSuccessResult(registerService.activate(userInfoJsonStr) == 1);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam("id") String id) throws GlobalErrorException {
        return registerService.test(id);
    }
}
