/**
 * 
 */
package com.home.jaxrs01.view;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.slf4j.simple.SimpleLogger;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author Ahmad Alrefai
 */
public class TestClient {
	
	public static void main(String[] args) {
		
		 System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
		
		Client client = JerseyClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/ahmad_jaxrs01/resources/teacher/testresponse").request().get();
		System.out.println("Get Data from Server after Get Request " + response.getStatus());
		
		
		response.close();
		client.close();  
		
	}

}
