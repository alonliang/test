package com.dingxin.recruit.test.steps.resumeModuleDelete;


import org.junit.Assert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.dingxin.recruit.test.Application;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoPinResumeModuleDeleteStep {
	
	@Given("^作为一个填写完二个或以上选填项的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	@When("^我在简历预览页面，选择一个选填项，点击编辑，跳转到编辑页面$")
	public void 我在简历预览页面选择一个选填项点击编辑跳转到编辑页面() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}

		
	@And("^我点击删除按钮，点击确定删除$")
    public void 我点击删除按钮点击确定删除() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	
	@Then("^提示删除成功，跳转到简历预览页面$")
	public void 提示删除成功跳转到简历预览页面() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	@When("^点击编辑$")
	public void 点击编辑() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	@And("^点击删除按钮，点击我再想想$")
    public void 点击删除按钮点击我再想想() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	@And("^点击取消，点击退出$")
    public void 点击取消点击退出() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	@Then("^跳转简历预览页面，模块未删除$")
	public void 跳转简历预览页面模块未删除() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
}
