package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumbe" +
                "r-ZhaoPinResumeIntegrityTest.json"},
        glue = "com.dingxin.recruit.test.steps.resumeIntegrity",
        features = "classpath:feature/sprint5/zhaopinResumeIntegrity.feature"
)
public class ZhaoPinResumeIntegrityTest {
}
