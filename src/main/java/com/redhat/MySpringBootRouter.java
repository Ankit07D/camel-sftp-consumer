package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.qpid.proton.engine.TransportException;
import org.springframework.stereotype.Component;


@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
    	
    	onException(JsonValidationException.class)
    	.handled(true)
    	.process("JsonValidate")
    	.log("Exception : ${body}");
    	
    	onException(TransportException.class)
    	.handled(true)
    	.redeliveryDelay(5000L)
    	.maximumRedeliveries(2)
    	.log("Connection Exception");
    	
    	
    	onException(Exception.class)
    	.log("Exception Occured")
    	.to("amqp:queue:exception-queue");
    	
    	JacksonDataFormat jacksonDF = new JacksonDataFormat();
		  JacksonXMLDataFormat jacksonXMLDF = new JacksonXMLDataFormat();
		  
		  jacksonDF.setEnableJacksonTypeConverter(true);
		  jacksonDF.setUnmarshalType(MyBean.class);
		  
		  jacksonXMLDF.setUnmarshalType(MyBean.class);
		  
    	from("{{sftp-url}}")
            .log("Data came from sftp : ${body}" )
            .to("json-validator:myschema.json")
            
            .unmarshal(jacksonDF)
            .marshal(jacksonXMLDF)
            .to("amqp:queue:test-queue")
  		  	.log("message pushed to queue");
    }

}
