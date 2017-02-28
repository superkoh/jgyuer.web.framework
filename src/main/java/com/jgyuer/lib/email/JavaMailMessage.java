package com.jgyuer.lib.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by KOH on 2017/2/3.
 * <p>
 * tripgm-server
 */
public class JavaMailMessage implements MailMessage {
    private final static Logger logger = LoggerFactory.getLogger(MailMessage.class);

    private String smtpHost;
    private String fromAddress;
    private String username;
    private String password;

    private Properties properties;

    public JavaMailMessage(String smtpHost, String fromAddress, String username, String password) {
        this.smtpHost = smtpHost;
        this.fromAddress = fromAddress;
        this.username = username;
        this.password = password;
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.user", username);
//        properties.setProperty("mail.password", password);
    }

    @Override
    public void sendMail(String to, MailTemplate template) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(template.getTitle());
            message.setContent(template.getContent(), "text/html");
            Transport.send(message);
            logger.info(template.toString());
        } catch (MessagingException mex) {
            logger.error(mex.getMessage() + "[" + template.toString() + "]");
        }
    }

    @Override
    public void sendMailBatch(List<String> to, MailTemplate template) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            to.forEach(address -> {
                try {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
                } catch (MessagingException ignored) {
                }
            });
            message.setSubject(template.getTitle());
            message.setContent(template.getContent(), "text/html");
            Transport.send(message);
            logger.info(template.toString());
        } catch (MessagingException mex) {
            logger.error(template.toString());
        }
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
