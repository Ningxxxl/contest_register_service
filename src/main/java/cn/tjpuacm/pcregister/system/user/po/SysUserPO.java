package cn.tjpuacm.pcregister.system.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author ningxy
 * @date 2018-10-20 20:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserPO {
    private Long id;
    private String email;
    private String studentId;
    private String school;
    private String institute;
    private String major;
    private String clazz;
    private String realName;
    private String phone;
    private Long isActivate;
    private String activationCode;
    private Long isDelete;
    private Long isEnable;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
}
