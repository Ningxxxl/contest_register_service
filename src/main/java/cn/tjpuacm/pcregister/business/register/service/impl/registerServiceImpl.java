package cn.tjpuacm.pcregister.business.register.service.impl;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ningxy
 * @date 2018-10-22 16:50
 */
@Slf4j
@Service
public class registerServiceImpl implements RegisterService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public int activate(SysUserPO userPO) {
        String token = String.valueOf(userPO.getActivationCode());
        int row = 0;
        try {
            Map<String, Object> claimsMap = JwtTokenUtils.getClaimsMap(token);
            String phone = String.valueOf(claimsMap.get("phone"));
            String studentId = String.valueOf(claimsMap.get("studentId"));

            boolean infoMatch = phone.equals(userPO.getPhone()) && studentId.equals(userPO.getStudentId());
            if (infoMatch) {
                userPO.setIsActivate(1L);
                row = sysUserService.insertUser(userPO);
            }
        } catch (Exception e) {
            log.error("验证出错，报名失败" + e.getMessage());
        }
        return row;
    }
}
