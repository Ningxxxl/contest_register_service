package cn.tjpuacm.pcregister.system.mail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author ningxy
 * @date 2018-10-21 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVO {
    /**
     * 接收人
     */
    private String recipient;

    /**
     * 接收人
     */
    private String[] recipientsArray;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 参数映射Map
     */
    private Map<String, Object> paramsMap;

    /**
     * Freemarker模板名称
     */
    private String templateName;
}
