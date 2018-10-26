package cn.tjpuacm.pcregister.config;

import cn.tjpuacm.pcregister.entity.ResultBody;
import cn.tjpuacm.pcregister.exception.GlobalErrorException;
import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import lombok.extern.slf4j.Slf4j;
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
    @ExceptionHandler(value = GlobalErrorException.class)
    public ResultBody errorHandlerOverJson(GlobalErrorException exception) {
        log.error(exception.getMessage(), exception);
        GlobalErrorInterface errorInfo = exception.getErrorInfo();
        return new ResultBody(errorInfo);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBody errorHandlerOverJson1(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResultBody(500, exception.getMessage(), null);
    }
}
