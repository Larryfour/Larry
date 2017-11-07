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
    private static final String SMTP_HOST = "smtp.exmail.qq.com";
    private static final String USER_NAME = "itadmin@xuebaedu.com";
    private static final String PASSWORD = "xue8jiaoyu";

    static final Logger logger = Logger.getLogger(EmailUtil.class);

    public static void sendSalesDailyReport(String date,String html) throws Exception {
        logger.info("send report start.");

        Session session = EmailUtil.getSession();
        MimeMessage message = new MimeMessage(session);
        InternetAddress[] toArray = new InternetAddress[]{
//                new InternetAddress("sunhao@xuebaedu.com"),
//                new InternetAddress("yangwenwen@xuebaedu.com"),
//                new InternetAddress("qimingxin@xuebaedu.com"),
                new InternetAddress("120644874@qq.com")
        };
        try {
            message.setSubject(date+"销售日报");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(USER_NAME));
            message.addRecipients(RecipientType.TO, toArray);



            message.setContent(html, "text/html;charset=utf-8");
            Transport.send(message);

            logger.info("send report successful.");
        } catch (Exception e) {
            logger.error(e, e);
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
        props.setProperty("mail.smtp.host", SMTP_HOST);
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, PASSWORD);
                    }
                });

        return session;
    }
}
