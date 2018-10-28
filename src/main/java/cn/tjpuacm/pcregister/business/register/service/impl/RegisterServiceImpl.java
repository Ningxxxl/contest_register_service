package cn.tjpuacm.pcregister.business.register.service.impl;

import cn.tjpuacm.pcregister.business.register.enums.RegisterEnum;
import cn.tjpuacm.pcregister.business.register.service.RegisterService;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.system.user.po.SysUserPO;
import cn.tjpuacm.pcregister.system.user.service.SysUserService;
import cn.tjpuacm.pcregister.util.SmsUtil;
import cn.tjpuacm.pcregister.util.TimeUtil;
import cn.tjpuacm.pcregister.util.UUIDUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    private long expireTime = 20;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public String generateActivationCode(String userInfoJsonStr) throws GlobalErrorException {
        SysUserPO userPO = JSON.parseObject(userInfoJsonStr, SysUserPO.class);
        userPO.setIsActivate(0L);
        final String activationCode = UUIDUtils.generateUUID().substring(26);
        userPO.setActivationCode(activationCode);
//        int row = sysUserService.insertUser(userPO);

        final String expireTimeStr = String.valueOf(TimeUnit.HOURS.convert(expireTime, TimeUnit.SECONDS)) + "小时";

//        if (row == 0) {
//            throw new GlobalErrorException(RegisterEnum.INSERT_FAILED);
//        } else {
        cacheCode(userPO.getPhone(), activationCode);
        String smsRes = SmsUtil.sendSingleSMS(templateId, smsSign, userPO.getPhone(), activationCode, expireTimeStr);

        final String cntPrefix = "cnt_sms_";
        final long timeToMiddleNight = TimeUtil.getMillisNextEarlyMorning();
        Object smsNumOfPhone = redisTemplate.opsForValue().get(cntPrefix + userPO.getPhone());
        if (smsNumOfPhone != null && 3 <= Long.valueOf(String.valueOf((smsNumOfPhone)))) {
            throw new GlobalErrorException(RegisterEnum.OPTION_EXCESS);
        }

        if (!"OK".equals(smsRes)) {
            throw new GlobalErrorException(smsRes);
        } else {
            redisTemplate.opsForValue().increment(cntPrefix + userPO.getPhone(), 1);
            redisTemplate.expire(cntPrefix + userPO.getPhone(), timeToMiddleNight, TimeUnit.MILLISECONDS);
            return smsRes;
        }
//        }
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
    public String test(String id) throws GlobalErrorException {
        String s = UUID.randomUUID().toString();
        long t = redisTemplate.opsForValue().increment("inc_" + id, 1);
        log.info(String.valueOf(t));
        if (t == 1) {
            redisTemplate.expire("inc_ttl_" + id, expireTime, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(id, s, expireTime, TimeUnit.SECONDS);
        } else if (t > 1) {
            throw new GlobalErrorException(RegisterEnum.OPTION_TOO_FAST);
        }
        return s;
    }

    /**
     * 缓存手机号和对应的验证码
     * 并限制指定时间内不能重复提交
     *
     * @param phone 手机号
     * @param code  验证码
     * @throws GlobalErrorException
     */
    private void cacheCode(String phone, String code) throws GlobalErrorException {
        final String countPrefix = "cnt_";
        long t = redisTemplate.opsForValue().increment(countPrefix + phone, 1);
        log.info(String.valueOf(t));
        if (t == 1) {
            redisTemplate.expire(countPrefix + phone, expireTime, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(phone, code, expireTime, TimeUnit.SECONDS);
        } else if (t > 1) {
            throw new GlobalErrorException(RegisterEnum.OPTION_TOO_FAST);
        }
    }


}