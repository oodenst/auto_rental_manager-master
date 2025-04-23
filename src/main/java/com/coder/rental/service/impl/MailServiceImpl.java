package com.coder.rental.service.impl;

import com.coder.rental.service.IMailService;
import com.coder.rental.vo.MailVo;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements IMailService {
    @Resource
    private JavaMailSender javaMailSender;
    /**
     * 发送邮件的函数。
     *
     * @param mail 邮件对象，包含邮件的发送者、接收者、主题和内容。
     * 无返回值，发送成功后不返回任何结果。
     */
    @Override
    public void sendMail(MailVo mail) {
        // 创建一个SimpleMailMessage对象用于设置邮件信息
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件的发送者
        message.setFrom(mail.getFrom());
        // 设置邮件的接收者
        message.setTo(mail.getTo());
        // 设置邮件的主题
        message.setSubject(mail.getSubject());
        // 设置邮件的内容
        message.setText(mail.getContent());
        // 设置邮件的抄送者，这里将发送者作为抄送人
        message.setCc(mail.getFrom());
        // 使用javaMailSender发送邮件
        javaMailSender.send(message);
    }
}
