package com.siemens.config;

import org.springframework.web.client.RestTemplate;

public class RestTemplateSingleton {

	
	private static final RestTemplate INSTANCE = new RestTemplate();
	
	public static RestTemplate getRestTemplate(){
		return INSTANCE;
	}
	
}
