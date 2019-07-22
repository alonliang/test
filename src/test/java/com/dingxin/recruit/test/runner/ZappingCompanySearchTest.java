package com.dingxin.recruit.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinCompanySearchTest.json"},
        glue = "com.dingxin.recruit.test.steps.jobCompanySearch",
        features = "classpath:feature/zhaopinCompanySearch.feature"
)
class ZhaopinCompanySearchTest {
}
