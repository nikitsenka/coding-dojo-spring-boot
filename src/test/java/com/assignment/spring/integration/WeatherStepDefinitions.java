package com.assignment.spring.integration;

import com.assignment.spring.client.openweathermap.WeatherClient;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherStepDefinitions {

    @LocalServerPort
    int port;

    @Autowired
    private WeatherClient weatherClient;
    /**
     * Http client used to call system under test.
     */
    private HttpClient httpClient;
    private String baseUrl;
    private HttpResponse<String> serviceResponse;

    @Before
    public void setupTestContext() {
        httpClient = HttpClient.newHttpClient();
        baseUrl = "http://localhost:" + port;
    }

    @Given("^Service operates successfully$")
    public void service_operates_successfully() {

    }

    @Given("^Service has  invalid appid configured$")
    public void service_misconfigured() {
        weatherClient.setAppId("");
    }

    @When("^Client asks service for weather in (.*) city$")
    public void ask_weather_in(String city) throws IOException, InterruptedException {
        serviceResponse = get("/weather?city=" + city);
    }

    @Then("^Service return weather info$")
    public void weather_returned() {
        assertEquals(200, serviceResponse.statusCode());
        assertThat(serviceResponse.body(), containsString("London"));
    }

    @Then("^Service return error$")
    public void error_returned() {
        assertEquals(500, serviceResponse.statusCode());
        assertThat(serviceResponse.body(), containsString("Internal Server Error"));
    }


    private HttpResponse<String> get(String urlPath) throws IOException, InterruptedException {
        return httpClient
                .send(HttpRequest.newBuilder()
                                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .uri(URI.create(baseUrl + urlPath))
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );
    }
}
