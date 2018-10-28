package cn.tjpuacm.pcregister.business.register.service.impl;

import cn.tjpuacm.pcregister.business.register.enums.RegisterEnum;
import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.util.SmsUtil;
import cn.tjpuacm.pcregister.util.UUIDUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author ningxy
 * @date 2018-10-22 16:50
 */
@Slf4j
@Service
@EnableTransactionManagement
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    RedisTemplate redisTemplate;

    @Value("${smsService.templateId}")
    private int templateId;

    @Value("${smsService.smsSign}")
    private String smsSign;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public String generateActivationCode(String userInfoJsonStr) throws GlobalErrorException {
        SysUserPO userPO = JSON.parseObject(userInfoJsonStr, SysUserPO.class);
        userPO.setIsActivate(0L);
        final String activationCode = UUIDUtils.generateUUID().substring(26);
        userPO.setActivationCode(activationCode);
        int row = sysUserService.insertUser(userPO);

        if (row == 0) {
            throw new GlobalErrorException(RegisterEnum.INSERT_FAILED);
        } else {
            String smsRes = SmsUtil.sendSingleSMS(templateId, smsSign, userPO.getPhone(), activationCode, "24h");
            if (!"OK".equals(smsRes)) {
                throw new GlobalErrorException(smsRes);
            } else {
                return smsRes;
            }
        }
    }

    @Override
    public int activate(String userInfoJsonStr) {
        final SysUserPO userPO = JSON.parseObject(userInfoJsonStr, SysUserPO.class);
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

    @Override
    @Cacheable(value = "test", key ="#id", unless = "#result==null")
    public String test(String id) {
        return UUID.randomUUID().toString();
    }
}