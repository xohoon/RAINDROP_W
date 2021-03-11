package dev.drop.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
	JavaMailSender emailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
	}
	
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		try {
			emailSender.send(message);
		}catch(MailException es) {
			es.printStackTrace();
		}
	}
}
