package cn.tjpuacm.pcregister.business.account.service.impl;

import cn.tjpuacm.pcregister.business.account.dao.AccountRepository;
import cn.tjpuacm.pcregister.business.account.enums.AccountEnum;
import cn.tjpuacm.pcregister.business.account.service.AccountService;
import cn.tjpuacm.pcregister.business.account.vo.AccountVO;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ningxy
 * @date 2018-11-12 19:32
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Cacheable(value = "account")
    public AccountVO findAccount(String studentId, String phone) throws GlobalErrorException {
        AccountVO accountVO = accountRepository.findAccount(studentId, phone);
        if (accountVO == null) {
            throw new GlobalErrorException(AccountEnum.NOT_REGISTER);
        }
        return accountVO;
    }
}
