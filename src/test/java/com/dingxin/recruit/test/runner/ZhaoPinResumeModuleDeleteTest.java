package com.dingxin.recruit.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaoPinResumeModuleDeleteTest.json"},
        glue = "com.dingxin.recruit.test.steps.resumeModuleDelete",
        features = "classpath:feature/sprint5/zhaopinResumeModuleDelete.feature"
)
public class ZhaoPinResumeModuleDeleteTest {

}
