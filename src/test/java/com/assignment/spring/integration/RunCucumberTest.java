package com.assignment.spring.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.assignment.spring.integration"},
        plugin = {"pretty", "html:build/cucumber", "junit:build/report/junit/cucumber-report.xml"})
public class RunCucumberTest {

}
