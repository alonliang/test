package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinFacultySearchTest.json"},
        glue = "com.dingxin.recruit.test.steps.jobFacultySearch",
        features = "classpath:feature/zhaopinFacultySearch.feature"
)
public class ZhaopinFacultySearchTest {
}
