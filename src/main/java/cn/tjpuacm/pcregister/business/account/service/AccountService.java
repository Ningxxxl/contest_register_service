package cn.tjpuacm.pcregister.business.account.service;

import cn.tjpuacm.pcregister.business.account.vo.AccountVO;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;

/**
 * @author ningxy
 * @date 2018-11-12 19:31
 */
public interface AccountService {
    AccountVO findAccount(String studentId, String phone) throws GlobalErrorException;
}
