package com.hotelManagement.angular.hotelmanagement.user;

import org.springframework.stereotype.Component;
import java.util.Properties;
import java.util.Base64;  
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class UserUtils {
	   
       public void sendMail(String walletAmount,String reciever) {
             Base64.Decoder decoder = Base64.getDecoder();
           String encpwd="RC5LYXJ0aGlrQDIyMjI=";
           String pwd=new String(decoder.decode(encpwd));
           String to = reciever;
           String from = "bublububbie@gmail.com";
           String host = "smtp.gmail.com";
           Properties properties = System.getProperties();
           properties.put("mail.smtp.host", host);
           properties.put("mail.smtp.port", "465");
           properties.put("mail.smtp.ssl.enable", "true");
           properties.put("mail.smtp.auth", "true");
           Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication("bublububbie@gmail.com",pwd );
               }
           });
           session.setDebug(true);
           try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               message.setSubject("Updated Wallet Balance!");
               message.setText("Your updated wallet balance is "+walletAmount);
               Transport.send(message);
           } catch (MessagingException mex) {
               mex.printStackTrace();
           }
	    }   
}
