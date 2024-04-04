package org.project1.nhom8.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ngtnthori03
 */
public class MailService {

    private String sender;
    private Properties pro;
    private Session session;
    private boolean result;
    private Thread th;

    public boolean isResult() {
        return this.result;
    }

    public MailService() {
        this.pro = new Properties();
        this.result = false;
        this.th = new Thread();
        this.pro.putAll(new HashMap<String, String>() {
            {
                put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
                put("mail.smtp.port", "587"); // TLS Port
                put("mail.smtp.auth", "true"); // enable authentication
                put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
            }
        });
    }

    public boolean auth(String _sender, String _password) {
        try {
            this.session = Session.getInstance(pro, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(_sender, _password); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void send(String _receiver, String _subject, String _message) {
        try {
            Message msg = new MimeMessage(session);
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(_receiver, true));


            msg.setSubject(_subject);

            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(_message, "text/html; charset=utf-8");

            msg.setContent(new MimeMultipart(mbp));

            Transport.send(msg);
            result = true;
        } catch (MessagingException ex) {
            result = false;
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
