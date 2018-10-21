package cn.tjpuacm.pcregister.system.mail.service.impl;

import cn.tjpuacm.pcregister.system.mail.service.MailService;
import cn.tjpuacm.pcregister.system.mail.vo.MailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author ningxy
 * @date 2018-10-21 14:43
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 邮件的发送者
     */
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @Override
    public void sendMail(MailVO mailVO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(getMailSender());
            simpleMailMessage.setTo(mailVO.getRecipient());
            simpleMailMessage.setSubject(mailVO.getSubject());
            simpleMailMessage.setText(mailVO.getContent());
            javaMailSender.send(simpleMailMessage);
            log.info("邮件发送成功 [to:" + mailVO.getRecipient() + "]");
        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
        }
    }

    private String getMailSender() {
        return String.format("%s <%s>", nickname, username);
    }
}
