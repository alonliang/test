package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaoPinResumeCreateTest.json"},
        glue = "com.dingxin.recruit.test.steps.resumeCreate",
        features = "classpath:feature/sprint5/zhaopinResumeCreate.feature"
)
public class ZhaoPinResumeCreateTest {
}
