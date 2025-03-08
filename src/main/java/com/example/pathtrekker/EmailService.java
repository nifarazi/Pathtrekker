package com.example.pathtrekker;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailService {

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        // SMTP server properties
        String fromEmail = "pathtrekker72@gmail.com"; // Replace with your email
        String password = "ledp jkhy msau kikq"; // Replace with your email account's password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Use your SMTP server
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authenticate email session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Compose email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email: " + e.getMessage());
        }
    }
}