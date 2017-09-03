package com.gaurav.ws;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.MTOM;

//Service Implementation
@MTOM
@WebService(endpointInterface = "com.gaurav.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
	
	@Resource	
	WebServiceContext webServiceContext;
	
	@Override
	public String getHelloWorldAsString(String name) {

		MessageContext messageContext = webServiceContext.getMessageContext();

		// get request headers
		Map<?,?> requestHeaders = (Map<?,?>) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
		List<?> usernameList = (List<?>) requestHeaders.get("username");
		List<?> passwordList = (List<?>) requestHeaders.get("password");

		String username = "";
		String password = "";

		if (usernameList != null) {
			username = usernameList.get(0).toString();
		}

		if (passwordList != null) {
			password = passwordList.get(0).toString();
		}

		// of course this is not real validation
		// you should validate your users from stored databases credentials
		if (username.equals("user") && password.equals("pass")) {

			return "Valid User :" + username + " : "+name;

		} else {

			return "Unknown User!";
		}

		
	}



}
