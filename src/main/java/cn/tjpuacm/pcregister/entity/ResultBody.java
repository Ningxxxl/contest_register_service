package cn.tjpuacm.pcregister.entity;

import cn.tjpuacm.pcregister.enums.GlobalErrorEnum;
import cn.tjpuacm.pcregister.exception.GlobalErrorInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回体
 *
 * @author ningxy
 * @date 2018-10-24 17:23
 */
@ApiModel(value = "返回体")
@Data
public class ResultBody<T> {
    /**
     * 响应代码
     */
    @ApiModelProperty(value = "状态码", notes = "0表示成功，非0表示异常", example = "0")
    private Integer code;

    /**
     * 响应消息
     */
    @ApiModelProperty(value = "返回信息", example = "success")
    private String message;

    /**
     * 响应结果
     */
    @ApiModelProperty(value = "数据")
    private T data;

    public ResultBody() {
    }

    public ResultBody(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultBody(GlobalErrorInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public ResultBody<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public ResultBody<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultBody<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultBody<T> generateSuccessResult(T dataObj) {
        return new ResultBody<T>()
                .setCode(GlobalErrorEnum.SUCCESS.getCode())
                .setMessage(GlobalErrorEnum.SUCCESS.getMessage())
                .setData(dataObj);
    }
}
