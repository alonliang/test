package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaoPinRegisterTest.json"},
        glue = "com.dingxin.recruit.test.steps.register",
        features = "classpath:feature/zhaopinRegister.feature"
)
public class ZhaoPinRegisterTest {
}


