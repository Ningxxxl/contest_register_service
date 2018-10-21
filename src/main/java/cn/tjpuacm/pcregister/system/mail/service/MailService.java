package cn.tjpuacm.pcregister.system.mail.service;

import cn.tjpuacm.pcregister.system.mail.vo.MailVO;

/**
 * @author ningxy
 * @date 2018-10-21 14:45
 */
public interface MailService {
    void sendMail(MailVO mailVO);
}
