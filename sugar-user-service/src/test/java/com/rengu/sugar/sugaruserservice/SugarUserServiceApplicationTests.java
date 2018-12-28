package com.rengu.sugar.sugaruserservice;

import com.rengu.sugar.sugaruserservice.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SugarUserServiceApplicationTests {
    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("xqingliuwu@163.com", "测试邮件", " 你好哈哈哈哈");
    }

    @Test
    public void sendTemplateMail() throws MessagingException {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "1");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("xqingliuwu@163.com", "主题：这是模板邮件", emailContent);
    }

}
