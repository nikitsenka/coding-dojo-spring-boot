package com.assignment.spring.api;

import com.assignment.spring.client.openweathermap.WeatherClient;
import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import com.assignment.spring.domain.entity.WeatherEntity;
import com.assignment.spring.domain.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private WeatherRepository weatherRepository;


    @RequestMapping("/weather")
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
