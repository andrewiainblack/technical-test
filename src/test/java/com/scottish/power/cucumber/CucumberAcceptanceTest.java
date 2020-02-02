package com.scottish.power.cucumber;

import com.scottish.power.Application;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest(classes = Application.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberAcceptanceTest {
}