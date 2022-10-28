package com.example.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class OrdersApplication {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate () {
		return new RestTemplate();
		
	}
	
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/orders/*"))
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.build()
				.apiInfo(apiDetails());
	}
	
		private ApiInfo apiDetails() {
			return new ApiInfo("ORDERS API", 
					"ORDERS", 
					"1.0", 
					"FREE OF COST",
					new springfox.documentation.service.Contact("CHETAN", "localhost:8082/orders", "sai@gmail.com"), "API License", 
					"localhost:8081/product/getproducts",
					Collections.emptyList());
		
	}


}
