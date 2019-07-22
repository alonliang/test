package com.dingxin.recruit.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"json", "json:target/cucumber-ZhaopinAttachmentPreviewTest.json"},
        glue = "com.dingxin.recruit.test.steps.attachmentPreview",
        features = "classpath:feature/Sprint5/zhaopinAttachmentPreview.feature"
)
public class ZhaopinAttachmentPreviewTest {

}
