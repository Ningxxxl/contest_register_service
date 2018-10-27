package cn.tjpuacm.pcregister.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一错误码异常
 *
 * @author ningxy
 * @date 2018-10-24 17:20
 */
@Getter
@Setter
public class GlobalErrorException extends Exception{
    private GlobalErrorInterface errorInfo;

    public GlobalErrorException(GlobalErrorInterface errorInfo) {
        this.errorInfo = errorInfo;
    }

    public GlobalErrorException(String message) {
        this.errorInfo = new TempError(message);
    }
}

class TempError implements GlobalErrorInterface {
    private static final Integer TEMP_ERROR_CODE = 999;
    private String message;

    @Override
    public Integer getCode() {
        return TEMP_ERROR_CODE;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public TempError(String message) {
        this.message = message;
    }
}
