package com.shop.util;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
	
	public static void sendGMail(String to,String subject,String msg){
            //email co
            final String username = "sanasa.bank.noreply";
            final String password = "uzihuutmlwiurqpn";
            
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "465");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("sanasa.bank.noreply@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(to)
                );
                message.setSubject(subject);
                message.setContent(msg,"text/html");

                Transport.send(message);
                System.out.println("email sent to : "+to);
            } catch (javax.mail.MessagingException messagingException) {
                System.out.println("###### email error : "+messagingException.getMessage());
            }
            
        }
	
}
