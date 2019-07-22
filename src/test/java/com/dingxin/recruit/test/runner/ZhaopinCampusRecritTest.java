package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinCampusRecritTest.json"},
        glue = "com.dingxin.recruit.test.steps.apply",
        features = "classpath:feature/zhaopinCampusRecruit.feature"
)
public class ZhaopinCampusRecritTest {
}
