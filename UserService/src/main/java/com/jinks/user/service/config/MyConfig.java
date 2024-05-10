package com.jinks.user.service.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {


	@Bean
	@LoadBalanced	//this is used to call the microservices based on the service-name and not IP address and port
	public RestTemplate restTemplate() {
		
		
		return new  RestTemplate();
	}
	
}
