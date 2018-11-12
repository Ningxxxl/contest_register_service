package cn.tjpuacm.pcregister.business.account.dao;

import cn.tjpuacm.pcregister.business.account.vo.AccountVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author ningxy
 * @date 2018-11-12 19:36
 */
@Repository
public interface AccountRepository {

    /**
     * 查找用户的比赛账号
     *
     * @param studentId 学号
     * @param phone     手机号
     * @return 账号信息
     */
    @Select("select " +
            "  su.student_id as `studentId`, " +
            "  su.phone as `phone`, " +
            "  bca.username as `accountUsername`, " +
            "  bca.pwd as `accountPwd` " +
            "from " +
            "  sys_user su, " +
            "  rel_user_account r, " +
            "  bus_contest_account bca " +
            "where su.is_delete = 0 and su.is_enable = 1 " +
            "  and bca.is_delete = 0 and bca.is_enable = 1 " +
            "  and su.student_id = #{studentId} " +
            "  and su.phone = #{phone} " +
            "  and su.id = r.student_id " +
            "  and bca.id = r.account_id " +
            "limit 1")
    AccountVO findAccount(@Param("studentId") String studentId, @Param("phone") String phone);
}
