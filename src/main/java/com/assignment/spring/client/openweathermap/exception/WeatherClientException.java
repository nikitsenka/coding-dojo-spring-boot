package com.assignment.spring.client.openweathermap.exception;

/**
 * The exception represents all happening runtime issues that were cause by the openweathermap service interactions.
 */
public class WeatherClientException extends RuntimeException {

    /**
     * Constructor.
     * @param message
     */
    public WeatherClientException(String message) {
        super(message);
    }

}
