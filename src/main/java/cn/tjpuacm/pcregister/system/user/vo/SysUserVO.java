package cn.tjpuacm.pcregister.system.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningxy
 * @date 2018-10-21 10:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserVO {
    private Long id;
    private String email;
    private String studentId;
    private String school;
    private String institute;
    private String major;
    private String clazz;
    private String realName;
    private String phone;
}
