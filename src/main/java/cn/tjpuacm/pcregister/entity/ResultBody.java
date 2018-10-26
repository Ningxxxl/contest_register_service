package cn.tjpuacm.pcregister.entity;

import cn.tjpuacm.pcregister.enums.GlobalErrorEnum;
import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回体
 *
 * @author ningxy
 * @date 2018-10-24 17:23
 */
@Data
@AllArgsConstructor
public class ResultBody {
    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object data;

    public ResultBody(GlobalErrorInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public static ResultBody generateSuccessResult(Object dataObj) {
        return new ResultBody(
                GlobalErrorEnum.SUCCESS.getCode(),
                GlobalErrorEnum.SUCCESS.getMessage(),
                dataObj
        );
    }
}
