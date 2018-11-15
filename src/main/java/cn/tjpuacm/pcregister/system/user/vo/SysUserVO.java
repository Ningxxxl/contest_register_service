package cn.tjpuacm.pcregister.system.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningxy
 * @date 2018-10-21 10:35
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserVO {
    @ApiModelProperty(value = "businessId")
    private Long id;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "学号")
    private String studentId;
    @ApiModelProperty(value = "学校")
    private String school;
    @ApiModelProperty(value = "学院")
    private String institute;
    @ApiModelProperty(value = "专业")
    private String major;
    @ApiModelProperty(value = "班级")
    private String clazz;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "手机号码")
    private String phone;
}
