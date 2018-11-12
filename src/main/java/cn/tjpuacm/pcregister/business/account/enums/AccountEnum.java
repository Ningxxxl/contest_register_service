package cn.tjpuacm.pcregister.business.account.enums;

import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ningxy
 * @date 2018-11-12 20:46
 */
@AllArgsConstructor
public enum AccountEnum implements GlobalErrorInterface {
    /**
     * 未找到符合条件的比赛账号信息
     */
    NOT_REGISTER(3001, "未找到对应账号");

    @Getter
    private Integer code;

    @Getter
    private String message;
}
