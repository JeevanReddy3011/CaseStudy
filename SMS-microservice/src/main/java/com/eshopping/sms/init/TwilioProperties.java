package com.eshopping.sms.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("twilio")
public class TwilioProperties {

	private String accountSid;
	private String authToken;
	private String fromNumber;
	
}
