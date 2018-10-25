package cn.tjpuacm.pcregister.result;

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
public class GlobalErrorInfoHandler {
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(GlobalErrorInfoException exception) {
        log.error(exception.getMessage(), exception);
        ErrorInfoInterface errorInfo = exception.getErrorInfo();
        return new ResultBody(errorInfo);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBody errorHandlerOverJson1(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResultBody(500, exception.getMessage(), null);
    }
}
