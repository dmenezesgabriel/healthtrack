package com.healthtrack.bo;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.healthtrack.exception.EmailException;

public class EmailBO {
    Logger logger = java.util.logging.Logger.getLogger(this.getClass().getName());

    public void sendEmail(String recipient, String subject, String message) throws EmailException {
        final String username = System.getenv("SMTP_EMAIL");
        final String password = System.getenv("SMTP_PASSWORD");

        logger.info("Sending email from: " + username);
        logger.info("Sending email to: " + recipient);
        logger.info("Sending email with subject: " + message);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(username));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            email.setSubject(subject);
            email.setText(message);

            Transport.send(email);

        } catch (MessagingException e) {
            throw new EmailException("Error sending e-mail");
        }
    }
}