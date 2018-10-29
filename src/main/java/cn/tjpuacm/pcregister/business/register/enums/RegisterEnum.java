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

    LACK_OF_PARA(1101, "参数"),

    TOKEN_ERR(1200, "验证码无效"),

    TOKEN_OVER_TIME(1201, "验证码过期"),

    OPTION_TOO_FAST(1301, "您的操作过于频繁，休息休息一下~"),

    OPTION_EXCESS(1302, "您的操作已达上限，明日再来吧"),

    ;

    @Setter
    private Integer code;

    @Setter
    private String message;
}
