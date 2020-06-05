import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Weather REST API performance.
  */
class WeatherClientSimulation extends Simulation {

  val configurator: ScenarioСonfigurator = new ScenarioСonfigurator

  val getWeather = scenario("Send get request to get weather info")
    .exec(http("send_event")
      .get("/weather?city=London")
      .check(status.is(200)))
    .pause(5)

  setUp(configurator.defaultScenarioConfig(getWeather)).protocols(configurator.httpProtocol).assertions(configurator.defaultAssertions)
}
