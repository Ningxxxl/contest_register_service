package cn.tjpuacm.pcregister.system.mail.service.impl;

import cn.tjpuacm.pcregister.system.mail.service.MailService;
import cn.tjpuacm.pcregister.system.mail.vo.MailVO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * @author ningxy
 * @date 2018-10-21 14:43
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 邮件的发送者
     */
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.nickname}")
    private String nickname;

    /**
     * 发送简单邮件
     *
     * @param mailVO MailVO
     */
    @Override
    public void sendSimpleMail(MailVO mailVO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(getMailSender());
            simpleMailMessage.setTo(mailVO.getRecipient());
            simpleMailMessage.setSubject(mailVO.getSubject());
            simpleMailMessage.setText(mailVO.getContent());
            mailSender.send(simpleMailMessage);
            log.info("Send mail success. [to:" + mailVO.getRecipient() + "]");
        } catch (Exception e) {
            log.error("Send mail failed. ", e.getMessage());
        }
    }

    @Override
    public void sendMailFromTemplate(MailVO mailVO) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(getMailSender());
            messageHelper.setTo(mailVO.getRecipient());
            messageHelper.setSubject(mailVO.getSubject());

            try {
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate(mailVO.getTemplateName());
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailVO.getParamsMap());

                    messageHelper.setText(text, true);
                    mailSender.send(mimeMessage);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拼接昵称生成发送人信息
     * @return String 发送人
     */
    private String getMailSender() {
        return String.format("%s <%s>", nickname, username);
    }
}
