package com.birthday.manage;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import java.util.Properties;

public class EmailSender {

	public static boolean sendEmail(String to,String from,String subject,String text)
	{
		boolean flag = false;
		
		// Set up mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "your.smtp.server.com"); // Replace with your SMTP server
        properties.put("mail.smtp.port", "587"); // Replace with the SMTP server port (e.g., 587 for TLS)
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your_email@gmail.com", "password");
                // Replace with your email and password
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress(from));

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set the email subject
            message.setSubject(subject);

            // Set the email content
            message.setText(text);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
		
		return false;
		
	}
}
