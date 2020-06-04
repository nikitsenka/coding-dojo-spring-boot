Feature: Weather service

  Scenario: Current weather received successfully
    Given Service operates successfully
    When Client asks service for weather in London city
    Then Service return weather info

