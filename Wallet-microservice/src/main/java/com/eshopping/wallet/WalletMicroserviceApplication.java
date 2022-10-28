package com.eshopping.wallet;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WalletMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletMicroserviceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/wallet/**"))
				.apis(RequestHandlerSelectors.basePackage("com.eshopping.wallet"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Wallet API", 
				"APIs for wallet microservice", 
				"1.0", 
				"Free to use", 
				new Contact("Some User", "http://localhost:8083/", "mail123@gmail.com"), 
				"API License", 
				"http://localhost:8083",
				Collections.emptyList());
				
	}
	
}
