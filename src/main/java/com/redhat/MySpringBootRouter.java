package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
    	from("sftp:35.172.135.107:22/files?username=sftpuser&password=password&move=done")
            .log("Data came from sftp : ${body}" )
            .to("amqp:queue:test-queue")
  		  	.log("message pushed to queue");
    }

}
