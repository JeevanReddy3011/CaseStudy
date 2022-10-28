package com.eshopping.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.sms.init.TwilioProperties;
import com.eshopping.sms.model.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	private final TwilioProperties twilioProperties;
	
	@Autowired
	public SmsService(TwilioProperties twilioProperties) {
		this.twilioProperties = twilioProperties;
	}
	
	public void sendSms(SmsRequest smsRequest) {
	Message
		.creator(new PhoneNumber(smsRequest.getPhoneNumber()), 
				 new PhoneNumber("+16187654113"), 
				 smsRequest.getSms())
				 					.create();
	}
	
}
