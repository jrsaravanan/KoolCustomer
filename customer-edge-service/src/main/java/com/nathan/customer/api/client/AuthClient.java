package com.nathan.customer.api.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nathan.customer.api.dto.AuthToken;

@Component
public class AuthClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthClient.class);

	@Value("${auth-service.url}")
	private String authUrl;

	 @Autowired
	 private RestTemplate restTemplate;
	 
	  
	 
	 public boolean validateToken(String token) {
		 
		 LOGGER.info("validated token : token");
		 LOGGER.info("url ---- {} ", authUrl );
		 
		 HttpHeaders requestHeaders = new HttpHeaders();
		 requestHeaders.set("X-Auth-Token", token);
		 requestHeaders.set(HttpHeaders.ACCEPT,  MediaType.APPLICATION_JSON_VALUE);
		 requestHeaders.set(HttpHeaders.CONTENT_TYPE,  MediaType.APPLICATION_JSON_VALUE);
		 HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
		 
		 ParameterizedTypeReference<AuthToken> responseType = new ParameterizedTypeReference<AuthToken>() {
		};
		
		ResponseEntity<AuthToken> exchange = null;
		try { 
			exchange = restTemplate.exchange(authUrl, HttpMethod.GET, httpEntity,responseType );
		} catch (Exception e) {
			LOGGER.error("Authentication failed  -- {} " , e.getMessage() );
			return false;
		}
		 LOGGER.info(" exchange.getStatusCode()   {} " ,exchange.getStatusCode());        
	                        
		 return true;
	 }
}
