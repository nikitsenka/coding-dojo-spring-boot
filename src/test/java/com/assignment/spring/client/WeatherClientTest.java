package com.assignment.spring.client;

import com.assignment.spring.client.openweathermap.WeatherClient;
import com.assignment.spring.client.openweathermap.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherClient weatherClient;

    @BeforeEach
    void setUp() {
        weatherClient.setAppId("id");
        weatherClient.setWeatherApiUrl("test?q={city}&APPID={appid}");
    }

    @Test
    void getWeather_shouldReturnResponse_whenServiceAvailable() {
        WeatherResponse expected = new WeatherResponse();
        expected.setCod(200);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity(expected, HttpStatus.OK));

        WeatherResponse actual = weatherClient.getWeather("London");

        assertEquals(expected, actual);
        verify(restTemplate).getForEntity("test?q=London&APPID=id",
                WeatherResponse.class);
    }

    @Test
    void getWeather_shouldThrowException_whenBadParam() {
        assertThrows(IllegalArgumentException.class, () -> weatherClient.getWeather(null));
    }

    @Test
    void getWeather_shouldThrowException_whenServiceInternalBusinessError() {
        WeatherResponse response = new WeatherResponse();
        response.setCod(401);
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity(new WeatherResponse(), HttpStatus.OK));
        assertThrows(RuntimeException.class, () -> weatherClient.getWeather("London"));
    }

    @Test
    void getWeather_shouldThrowException_whenServiceInternalServerError() {
        when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity(new WeatherResponse(), HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(RuntimeException.class, () -> weatherClient.getWeather("London"));
    }

}
