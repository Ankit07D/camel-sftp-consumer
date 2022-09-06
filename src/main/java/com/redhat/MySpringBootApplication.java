package com.redhat;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBootApplication {


	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@Bean
	JmsConnectionFactory jmsConnectionFactory() {
		JmsConnectionFactory factory = new JmsConnectionFactory("admin", "admin",
				"amqps://amq-broker-amqp-0-svc-rte-amq-broker.apps.cluster-fbc5v.fbc5v.sandbox867.opentlc.com:443?transport.trustStoreLocation=client.ts&transport.trustStorePassword=password");
		return factory;
	}
}
