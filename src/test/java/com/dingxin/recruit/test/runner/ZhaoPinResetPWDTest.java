package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaoPinResetPWDTest.json"},
        glue = "com.dingxin.recruit.test.steps.resetpwd",
        features = "classpath:feature/zhaopinResetPWD.feature"
)
public class ZhaoPinResetPWDTest {
}
