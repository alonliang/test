package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaoPinLoginTest.json"},
        glue = "com.dingxin.recruit.test.steps.login",
        features = "classpath:feature/zhaopinLogin.feature"
)
public class ZhaoPinLoginTest {
}
