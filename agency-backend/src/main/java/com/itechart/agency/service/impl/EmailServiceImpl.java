package com.itechart.agency.service.impl;


import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailServiceImpl {
    private static final String username;
    private static final String password;
    private static final Session session;
    private static final Properties props = new Properties();
//   private static final Logger log = Logger.getLogger(EmailServiceImpl.class);

    static {
        try {
            props.load(EmailServiceImpl.class.getClassLoader().getResourceAsStream("email.properties"));
        } catch (IOException e) {
//            log.error(e);
        }
        username = props.getProperty("username");
        password = props.getProperty("password");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }

    public static void send(final String to, final String subject, final String text) throws MessagingException {
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);
//        log.info("message was send");
    }

}
