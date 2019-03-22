package com.ujiuye.usual.controller;

import com.ujiuye.usual.bean.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("/email")
public class EmailController {


     @RequestMapping("send")
     public String send(Email email){
         // 发件人的邮箱和密码
         String myEmailAccount = "15169115573@163.com";
         String myEmailPassword = "wjd815711";// 邮箱密码

        // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
        // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
        String myEmailSMTPHost = "smtp.163.com";
        // 收件人邮箱
        String receiveMailAccount = "573507894@qq.com";

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证


         // 创建验证器
         Authenticator auth = new Authenticator() {
             public PasswordAuthentication getPasswordAuthentication() {
                 // 密码验证
                 return new PasswordAuthentication("15169115573", "wjd123456");// 邮箱的授权码
             }
         };

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互（当前java程序和网易邮箱服务器之间的关联）
        Session session = Session.getInstance(props,auth);

        session.setDebug(true);
        try {
            // 3. 创建一封邮件（会话 发件邮箱账号 ，接收邮件的账号）
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            transport.connect(myEmailAccount, myEmailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
     }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, "清单", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("打折钜惠", "UTF-8");


        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();

        // 消息
        messageBodyPart.setText("消息");

        // 创建多重消息
        Multipart multipart = new MimeMultipart();

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);

        // 附件部分
        messageBodyPart = new MimeBodyPart();
        // 设置要发送附件的文件路径
        DataSource source = new FileDataSource("C:\\Users\\Administrator\\Desktop\\项目\\03\\abc.xls");
        messageBodyPart.setDataHandler(new DataHandler(source));

        // messageBodyPart.setFileName(filename);
        // 处理附件名称中文（附带文件路径）乱码问题
        messageBodyPart.setFileName(MimeUtility.encodeText("C:\\Users\\Administrator\\Desktop\\项目\\03\\abc.xls"));
        multipart.addBodyPart(messageBodyPart);

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(multipart);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }







}
