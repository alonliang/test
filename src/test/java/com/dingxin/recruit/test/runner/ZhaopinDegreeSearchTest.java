package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinDegreeSearchTest.json"},
        glue = "com.dingxin.recruit.test.steps.jobDegreeSearch",
        features = "classpath:feature/zhaopinDegreeSearch.feature"
)
public class ZhaopinDegreeSearchTest {
}
