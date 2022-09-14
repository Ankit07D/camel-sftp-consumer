package com.redhat;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBootApplication {

	@Value("${amq-username}")
	String amq_admin;
	
	@Value("${amq-password}")
	String amq_password;
	
	@Value("${amq-url}")
	String amq_url;

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@Bean
	JmsConnectionFactory jmsConnectionFactory() {
		JmsConnectionFactory factory = new JmsConnectionFactory(amq_admin, amq_password,amq_url);
		return factory;
	}
	
	
}
