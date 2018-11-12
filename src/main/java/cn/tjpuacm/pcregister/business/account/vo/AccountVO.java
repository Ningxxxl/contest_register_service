package cn.tjpuacm.pcregister.business.account.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningxy
 * @date 2018-11-12 20:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private String studentId;
    private String phone;
    private String accountUsername;
    private String accountPwd;
}
