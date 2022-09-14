package com.redhat;

import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ValidationException;
import org.apache.camel.component.jsonvalidator.JsonValidationException;
import org.springframework.stereotype.Component;

import com.networknt.schema.ValidationMessage;

@Component("JsonValidate")
public class JsonValidate implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		final ValidationException ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, ValidationException.class);
		String validationMessage = "";
		if (ex instanceof JsonValidationException) {
		    Set<ValidationMessage> errors = ((JsonValidationException)ex).getErrors();
		    for (ValidationMessage e : errors) {
		    	validationMessage = validationMessage + " " + e.toString().substring(2);
		    }
		    exchange.getIn().setBody(validationMessage);
		}
		
	}

}
