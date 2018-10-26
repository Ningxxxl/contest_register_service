package cn.tjpuacm.pcregister.business.register.service.impl;

import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.util.SmsUtil;
import cn.tjpuacm.pcregister.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ningxy
 * @date 2018-10-22 16:50
 */
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SysUserService sysUserService;

    @Value("${smsService.templateId}")
    private int templateId;

    @Value("${smsService.smsSign}")
    private String smsSign;

    @Override
    public String generateActivationCode(String phone, String studentId) {
        SysUserPO userPO = new SysUserPO();
        userPO.setIsActivate(0L);
        userPO.setPhone(phone);
        userPO.setStudentId(studentId);
        final String activationCode = UUIDUtils.generateUUID().substring(24);
        userPO.setActivationCode(activationCode);
        int row = sysUserService.insertUser(userPO);

        String smsRes = SmsUtil.sendSingleSMS(templateId, smsSign, phone, activationCode, "24h");
        if (row == 1 && "OK".equals(smsRes)) {
            return smsRes;
        } else {
            return "error: " + row + ", " + smsRes;
        }
    }

    @Override
    public int activate(SysUserPO userPO) {
        final String studentId = String.valueOf(userPO.getStudentId());
        final String activationCodeRes = sysUserService.getActivationCodeByStudentId(studentId);

        final boolean isValid = activationCodeRes.equals(userPO.getActivationCode());
        int row = 0;
        if (isValid) {
            userPO.setIsActivate(1L);
            row = sysUserService.updateUserByStudentId(userPO);
        }
        return row;
    }
}
