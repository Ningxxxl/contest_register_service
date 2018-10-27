package cn.tjpuacm.pcregister.enums;

import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 应用系统级别的错误码
 *
 * @author ningxy
 * @date 2018-10-24 17:29
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorEnum implements GlobalErrorInterface {
    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 未找到服务
     */
    NOT_FOUND(-1, "service not found");

    @Setter
    private Integer code;

    @Setter
    private String message;
}
