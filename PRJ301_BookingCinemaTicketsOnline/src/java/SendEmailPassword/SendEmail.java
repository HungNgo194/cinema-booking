package SendEmailPassword;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    final String senderEmail = "ngohung19042004@gmail.com"; //change email address
    final String senderPassword = "rrzi ikap aqzo zzqr"; //change password
    final String emailSMTPserver = "smtp.gmail.com";
    final String emailServerPort = "465";
    String receiverEmail = null;
    static String emailSubject;
    static String emailBody;

    public String sendRandom() {
        Random rd = new Random();
        int num = rd.nextInt(99999);
        return String.format("%05d", num);
    }

    public SendEmail() {

    }

    public SendEmail(String receiverEmail, String subject, String body) {
        //receiver email
        this.receiverEmail = receiverEmail;
        //subject
        this.emailSubject = subject;
        //body
        this.emailBody = body;

        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", emailSMTPserver);
            pr.setProperty("mail.smtp.port", emailServerPort);
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", emailServerPort);
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(this.receiverEmail));
            msg.setSubject(this.emailSubject);
            msg.setText(this.emailBody);

            Transport.send(msg);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
