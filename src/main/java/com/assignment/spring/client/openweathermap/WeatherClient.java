package com.assignment.spring.client.openweathermap;

import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    /**
     * Http client for interactions.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * App id for authentication.
     */
    @Value("${client.openweathermap.app.id}")
    private String appId;

    /**
     * Url template for getting weather by city.
     */
    @Value("${client.openweathermap.url}")
    private String weatherApiUrl;

    /**
     * Get weather info by city name.
     * @param city name
     * @return openweathermap response
     */
    public WeatherResponse getWeather(String city) {
        Assert.notNull(city, "city param required");
        String url = weatherApiUrl.replace("{city}", city).replace("{appid}", appId);
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.getForEntity(url, WeatherResponse.class);
        WeatherResponse body = responseEntity.getBody();
        if (SUCCESSFUL != responseEntity.getStatusCode().series()
                || !Integer.valueOf(HttpStatus.OK.value()).equals(body.getCod())) {
            //TODO enhance error response info
            throw new RuntimeException("weather service return error");
        }
        return body;
    }

    /**
     * Setter for app id.
     * @param appIdP
     */
    public void setAppId(String appIdP) {
        this.appId = appIdP;
    }

    /**
     * Setter for api url.
     * @param weatherApiUrlP
     */
    public void setWeatherApiUrl(String weatherApiUrlP) {
        this.weatherApiUrl = weatherApiUrlP;
    }
}
