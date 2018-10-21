package cn.tjpuacm.contest_register_service.system.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningxy
 * @date 2018-10-20 20:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserPO {
    private long id;
    private String email;
    private String studentId;
    private String school;
    private String institute;
    private String major;
    private String clazz;
    private String realName;
    private String phone;
    private long isDelete;
    private long isEnable;
}
