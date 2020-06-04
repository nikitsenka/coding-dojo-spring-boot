package com.assignment.spring.client.openweathermap;

import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpStatus.Series.SUCCESSFUL;

/**
 * Http client used to interact with openweathermap service.
 */
@Component
public class WeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.openweathermap.app.id}")
    private String appId;

    @Value("${client.openweathermap.url}")
    private String weatherApiUrl;

    public WeatherResponse getWeather(String city) {
        Assert.notNull(city, "city param required");
        String url = weatherApiUrl.replace("{city}", city).replace("{appid}", appId);
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.getForEntity(url, WeatherResponse.class);
        if (responseEntity.getStatusCode().series() != SUCCESSFUL || !Integer.valueOf(200).equals(responseEntity.getBody().getCod())) {
            //TODO enhance error response info
            throw new RuntimeException("weather service return error");
        }
        return responseEntity.getBody();
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setWeatherApiUrl(String weatherApiUrl) {
        this.weatherApiUrl = weatherApiUrl;
    }
}
