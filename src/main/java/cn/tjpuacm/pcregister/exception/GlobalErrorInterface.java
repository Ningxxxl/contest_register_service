package cn.tjpuacm.pcregister.exception;

/**
 * 错误码接口
 *
 * @author ningxy
 * @date 2018-10-24 17:22
 */
public interface GlobalErrorInterface {
    Integer getCode();

    String getMessage();
}
