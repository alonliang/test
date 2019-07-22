package com.dingxin.recruit.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinAttachmentUploadTest.json"},
        glue = "com.dingxin.recruit.test.steps.attachmentUpload",
        features = "classpath:feature/Sprint5/zhaopinAttachmentUpload.feature"
)
public class ZhaopinAttachmentUploadTest {

}
