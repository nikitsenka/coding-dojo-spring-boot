package com.assignment.spring.client.openweathermap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Contain beans and config required only for openweathermap client.
 */
@Configuration
public class ClientConfig {

    /**
     * Http client for interactions.
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
