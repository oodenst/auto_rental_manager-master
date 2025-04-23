package com.coder.rental.utils;

import com.coder.rental.service.IFinanceService;
import com.coder.rental.service.IMailService;
import com.coder.rental.vo.MailVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SendMailUtil {
    @Resource
    private IFinanceService financeService;
    @Resource
    private IMailService mailService;
    @Value("${spring.mail.username}")
    private String from;
//    @Scheduled(cron = "*/20 * * * * ?")
//    @Scheduled(cron = "0 0 20 ? * *")
    public void sendMail(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("今日收入：").append("租金收入：")
                .append(financeService.sumRentPay().getCountRentActual())
                .append("押金收入")
                .append(financeService.sumDeposit());
        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setTo("2297267622@qq.com");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        mailVo.setSubject(date+"收入");
        mailVo.setContent(stringBuffer.toString());
        mailService.sendMail(mailVo);
    }
}
