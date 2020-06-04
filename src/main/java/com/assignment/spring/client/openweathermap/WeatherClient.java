package com.assignment.spring.client.openweathermap;

import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

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
        return restTemplate.getForEntity(url, WeatherResponse.class).getBody();
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setWeatherApiUrl(String weatherApiUrl) {
        this.weatherApiUrl = weatherApiUrl;
    }
}
