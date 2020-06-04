Feature: Weather service

  Scenario: Current weather info received successfully
    Given Service operates successfully
    When Client asks service for weather in London city
    Then Service return weather info

  Scenario: Error getting weather info
    Given Service has invalid appid configured
    When Client asks service for weather in London city
    Then Service return error
