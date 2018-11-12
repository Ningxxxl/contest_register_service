package cn.tjpuacm.pcregister.business.account.controller;

import cn.tjpuacm.pcregister.business.account.service.AccountService;
import cn.tjpuacm.pcregister.entity.ResultBody;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ningxy
 * @date 2018-11-12 17:22
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultBody getAccount(@Param("studentId") String studentId, @Param("phone") String phone) throws GlobalErrorException {
        if (StringUtils.isEmpty(studentId) || StringUtils.isEmpty(phone)) {
            throw new GlobalErrorException("请填写完整参数");
        }
        return ResultBody.generateSuccessResult(accountService.findAccount(studentId, phone));
    }
}
