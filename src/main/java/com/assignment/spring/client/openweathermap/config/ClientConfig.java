package com.assignment.spring.client.openweathermap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate restTemplate(ResponseErrorHandler responseErrorHandler) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }
}
