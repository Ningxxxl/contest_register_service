package cn.tjpuacm.pcregister.business.register.service.impl;

import cn.tjpuacm.pcregister.business.register.enums.RegisterEnum;
import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.util.SmsUtil;
import cn.tjpuacm.pcregister.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Transactional
    public String generateActivationCode(@RequestParam("phone") String phone, @RequestParam("studentId") String studentId) throws GlobalErrorException {
        SysUserPO userPO = new SysUserPO();
        userPO.setIsActivate(0L);
        userPO.setPhone(phone);
        userPO.setStudentId(studentId);
        final String activationCode = UUIDUtils.generateUUID().substring(26);
        userPO.setActivationCode(activationCode);
        int row = sysUserService.insertUser(userPO);

        if (row == 0) {
            throw new GlobalErrorException(RegisterEnum.INSERT_FAILED);
        } else {
            String smsRes = SmsUtil.sendSingleSMS(templateId, smsSign, phone, activationCode, "24h");
            if (!"OK".equals(smsRes)) {
                throw new GlobalErrorException(smsRes);
            } else {
                return smsRes;
            }
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