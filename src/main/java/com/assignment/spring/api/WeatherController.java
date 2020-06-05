package com.assignment.spring.api;

import com.assignment.spring.client.openweathermap.WeatherClient;
import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import com.assignment.spring.domain.entity.WeatherEntity;
import com.assignment.spring.domain.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for weather functionality.
 */
@RestController
public class WeatherController {

    /**
     * Weather client.
     */
    @Autowired
    private WeatherClient weatherClient;

    /**
     * Weather repository.
     */
    @Autowired
    private WeatherRepository weatherRepository;

    /**
     * Rest API for getting weather info.
     *
     * @param city name
     * @return weather entity
     */
    @GetMapping("/weather")
    public WeatherEntity weather(@RequestParam String city) {
        return mapper(weatherClient.getWeather(city));
    }

    private WeatherEntity mapper(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(response.getName());
        entity.setCountry(response.getSys().getCountry());
        entity.setTemperature(response.getMain().getTemp());

        return weatherRepository.save(entity);
    }
}
