package cn.tjpuacm.pcregister.business.register.enums;

import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ningxy
 * @date 2018-10-26 18:51
 */
@AllArgsConstructor
@Getter
public enum RegisterEnum implements GlobalErrorInterface {

    /**
     * 信息插入失败
     */
    INSERT_FAILED(1100, "信息已存在"),

    TOKEN_ERR(1201, "验证码无效"),

    TOKEN_OVER_TIME(1202, "验证码过期"),

    ;

    @Setter
    private Integer code;

    @Setter
    private String message;
}
