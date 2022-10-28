package com.eshopping.sms.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshopping.sms.init.MQConfig;
import com.eshopping.sms.model.SmsRequest;

import lombok.Getter;

@Component
@Getter
public class MessageListener {
	
	@Autowired
	private SmsService smsService;

	@RabbitListener(queues = MQConfig.SMS_QUEUE)
	public void getMessageFromQueue(SmsRequest smsRequest) {
		smsService.sendSms(smsRequest);
	}
}
