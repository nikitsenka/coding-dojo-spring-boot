package com.assignment.spring.client.openweathermap.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.SUCCESSFUL;

@Component
public class WeatherClientErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (httpResponse.getStatusCode().series() != SUCCESSFUL);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() != SUCCESSFUL) {
            new RuntimeException("weather service return error");
        }
    }
}
