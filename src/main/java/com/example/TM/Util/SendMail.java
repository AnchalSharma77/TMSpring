package com.example.TM.Util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
		OTP o = new OTP();

	   public void sendFromGMail(  String to , String body) throws MessagingException {
	        Properties props = System.getProperties();
	        String from = "project7719@gmail.com";
	        String pass= "emailpassword";
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "false");
	        
	        
	        String subject = "OTP";
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);
	        

	        try {

	            message.setFrom(new InternetAddress(from));
	            InternetAddress toAddress = new InternetAddress();
/*
	            // To get the array of addresses
	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }*/
	            
	            toAddress = new InternetAddress(to);
	            
	            message.addRecipient(Message.RecipientType.TO, toAddress);
	            message.setSubject(subject);
	          //  message.setText(body);
	        	message.setContent(body, "text/html");
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	    }

	
}
