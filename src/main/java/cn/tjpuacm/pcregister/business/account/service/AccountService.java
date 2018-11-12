package cn.tjpuacm.pcregister.business.account.service;

import cn.tjpuacm.pcregister.business.account.vo.AccountVO;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;

/**
 * @author ningxy
 * @date 2018-11-12 19:31
 */
public interface AccountService {
    /**
     * 查找用户的比赛账号
     *
     * @param studentId 学号
     * @param phone     手机号
     * @return 账号信息
     * @throws GlobalErrorException
     */
    AccountVO findAccount(String studentId, String phone) throws GlobalErrorException;
}
