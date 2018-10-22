package cn.tjpuacm.pcregister.business.register.controller;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@RequestParam("phone") String phone, @RequestParam("studentId") String studentId) {
        return registerService.generateActivationCode(phone, studentId);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public boolean activate(SysUserPO userPO) {
        return registerService.activate(userPO) == 1;
    }
}
