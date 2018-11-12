package cn.tjpuacm.pcregister.exception;

/**
 * 错误码接口
 *
 * @author ningxy
 * @date 2018-10-24 17:22
 */
public interface GlobalErrorInterface {
    /**
     * 获取错误码
     * @return 错误码
     */
    Integer getCode();

    /**
     * 获取错误内容
     * @return 错误内容
     */
    String getMessage();
}
