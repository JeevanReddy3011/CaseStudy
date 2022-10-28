package com.eshopping.sms.init;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

//	private TwilioProperties twilioProperties;
	
	public TwilioInitializer() {
		Twilio.init("AC978243c9b091cd2b3d7473013ff8b850", "704ec2aee8ca1f0c19f8598cbda2df9e");
	}
	
}
