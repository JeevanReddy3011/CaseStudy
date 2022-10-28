package com.eshopping.email.model;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.eshopping.email.config.MQConfig;

import lombok.Getter;

@Component
@Getter
public class MessageListener {
	
//	private EmailBody email;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromMail;
	
	public void sendEmail(String toEmail,
			  String body,
			  String subject) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(fromMail);
		message.setTo(toEmail);
		message.setText(body);	
		message.setSubject(subject);

		mailSender.send(message);
	}

	@RabbitListener(queues = MQConfig.QUEUE)
	public void getMessageFromQueue(EmailBody email) {
		sendEmail(email.getToMail(), email.getBody(), email.getSubject());
	}
	
	@RabbitListener(queues = MQConfig.EMAIL_QUEUE)
	public void getMessageFromEmailQueue(EmailBody email) {
		sendEmail(email.getToMail(), email.getBody(), email.getSubject());
	}
	
}
