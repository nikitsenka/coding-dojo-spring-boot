import java.util.concurrent.TimeUnit

import io.gatling.core.Predef.{Assertion, constantUsersPerSec, global, rampUsersPerSec, _}
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

import scala.concurrent.duration.FiniteDuration

/**
  * Configuration for all performance tests.
  */
class ScenarioСonfigurator {
  val httpProtocol = http.baseUrl(sys.env.getOrElse("TARGET_HOST_URL", "http://localhost:8080"))
  val startAmountOfRequestsPerSecond: Double = sys.env.getOrElse("START_AMOUNT_OF_REQUESTS", "1").toDouble
  val endAmountOfRequestsPerSecond: Double = sys.env.getOrElse("END_AMOUNT_OF_REQUESTS", "10").toDouble
  val duration: FiniteDuration = FiniteDuration(sys.env.getOrElse("PERFORMANCE_TEST_DURATION", "60").toLong, TimeUnit.SECONDS)
  val meanResponseTime: Int = sys.env.getOrElse("ASSERTION_MEAN_RESPONSE_TIME", "70").toInt

  /**
    * Apply default scenario configurations.
    *
    * @param scenario
    * @return population builder
    */
  def defaultScenarioConfig(scenario: ScenarioBuilder): List[PopulationBuilder] = {
    List(scenario.inject(
      rampUsersPerSec(startAmountOfRequestsPerSecond) to endAmountOfRequestsPerSecond during duration,
      constantUsersPerSec(endAmountOfRequestsPerSecond) during duration))
  }

  /**
    * Add default assertions for scenario.
    *
    * @return assertions list
    */
  def defaultAssertions: List[Assertion] = {
    List(global.successfulRequests.percent.is(100), global.responseTime.mean.lt(meanResponseTime))
  }
}
