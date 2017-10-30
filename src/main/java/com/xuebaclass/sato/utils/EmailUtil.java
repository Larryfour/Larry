package com.xuebaclass.sato.utils;

import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
    private static final String smtphost = "smtp.exmail.qq.com";
    private static final String username = "itadmin@xuebaedu.com";
    private static final String password = "xue8jiaoyu";

    static final Logger LOG = Logger.getLogger(EmailUtil.class);

    public static void sendSalesDailyReport(String date,String html) throws Exception {
        LOG.info("sendSalesDailyReport by sunhao@xuebaedu.com");

        Session session = EmailUtil.getSession();
        MimeMessage message = new MimeMessage(session);
        InternetAddress[] toArray = new InternetAddress[]{
                new InternetAddress("sunhao@xuebaedu.com"),
                new InternetAddress("yangwenwen@xuebaedu.com"),
//                new InternetAddress("qimingxin@xuebaedu.com"),
                new InternetAddress("120644874@qq.com")
        };
        try {
            message.setSubject(date+"销售日报");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(username));
            message.addRecipients(RecipientType.TO, toArray);



            message.setContent(html, "text/html;charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            LOG.error(e, e);
        }
    }

    public static String loadRestPwdMailHtml(String htmlName) throws IOException {
        InputStream is = EmailUtil.class.getClassLoader().getResourceAsStream(htmlName);
        InputStreamReader inputReader = new InputStreamReader(is);
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String line = null;
        StringBuffer strBuffer = new StringBuffer();
        while ((line = bufferReader.readLine()) != null) {
            strBuffer.append(line);
        }
        return strBuffer.toString();
    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", smtphost);
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        //props.setProperty("mail.debug", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }
}
