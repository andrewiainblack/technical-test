package com.scottish.power.cucumber;

import com.scottish.power.Application;
import cucumber.api.java.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class CucumberRoot {
    @Before
    public void before() {
        //Dummy to load up context
    }
}