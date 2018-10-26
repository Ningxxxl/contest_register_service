package cn.tjpuacm.pcregister.exception;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class GlobalErrorException extends Exception{
    private GlobalErrorInterface errorInfo;
}
