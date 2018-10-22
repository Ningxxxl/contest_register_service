package cn.tjpuacm.pcregister.business.register.controller;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ningxy
 * @date 2018-10-21 19:46
 */
@RestController
@RequestMapping("/register")
public class registerController {
    @Autowired
    private RegisterService registerService;

    @Value("${jwt.properties.emailActivateExpiration}")
    private long expiration;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String register(@RequestParam("phone") String email, @RequestParam("studentId") String studentId) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("phone", email);
        claimsMap.put("studentId", studentId);
        return JwtTokenUtils.generateToken(claimsMap, expiration);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    public boolean activate(SysUserPO userPO) {
        return registerService.activate(userPO) == 1;
    }
}
