package org.demo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/org/demo/", glue = "org.demo.steps")
public class ITCucumberTest {
}

