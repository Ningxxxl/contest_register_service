package cn.tjpuacm.pcregister.config;

import cn.tjpuacm.pcregister.entity.ResultBody;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import cn.tjpuacm.pcregister.system.mail.service.MailService;
import cn.tjpuacm.pcregister.system.mail.vo.MailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一错误码异常处理
 *
 * @author ningxy
 * @date 2018-10-24 17:16
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {
    @Autowired
    MailService mailService;

    @ExceptionHandler(value = GlobalErrorException.class)
    public ResultBody errorHandlerOverJson(GlobalErrorException exception) {
        log.error(exception.toString());
        GlobalErrorInterface errorInfo = exception.getErrorInfo();
        return new ResultBody(errorInfo);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBody errorHandlerOverJson1(Exception exception) {
        log.error(exception.getMessage(), exception);
        MailVO mailVO = new MailVO();
        mailVO.setRecipient("16847955@qq.com");
        mailVO.setSubject("TJPUPC_注册服务_异常提醒");
        mailVO.setContent(exception.getMessage() + "\n\n\n" +  exception);
        mailService.sendSimpleMail(mailVO);
        return new ResultBody(500, exception.getMessage());
    }
}
