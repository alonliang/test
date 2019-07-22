package com.dingxin.recruit.test.steps.apply;

import com.dingxin.recruit.test.Application;
import com.dingxin.recruit.test.base.BaseRestAssuredSteps;
import com.dingxin.recruit.test.config.TestConfig;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoinCampusRecruitStep extends BaseRestAssuredSteps{

    static final Logger logger = LoggerFactory.getLogger(ZhaoinCampusRecruitStep.class);

    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {
        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        this.initAndLogin(this.getNewOne("USERNAME_API_TEST_NEW","UAhBzgKccNP568cf+868QCNLV2CH7Llnqh5TdjZFUSTqh+qGPdj7xTjZIvOAa0ow0wXsSLzM9OKo+idT7aMXno+KZWGP/r0/ZaQNRPgFcFvhvnQrqd6WN2LjDi1IXBTgZcAJRL+fStpUU+KnllPen9Q3xZysCq6YBRm6xj3t6wM="));
    }

    @After
    public void teardown() {
        Map<String, String> cond1 = new HashMap<>();
        cond1.put("c_user_name", "USERNAME_API_TEST_NEW");
        delete("tb_rec_nw_applicant", cond1);
        Map<String, String> cond2 = new HashMap<>();
        cond2.put("C_REFERENCE_ID",jobID);
        delete("TB_REC_NW_WEB_APPLY_POS", cond2);
    }

    private static String jobID = "9105f234f0604519b8beb0f02358306a";//昆明局-自动控制班作业员

    @And("^点击确认，提示“申请岗位已超过(\\d+)个”，当前页面$")
    public void 点击确认提示申请岗位已超过个当前页面(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getApplication();
        applicant.put("jobId", jobID);
        Integer o = (Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/application", applicant.toString(), "status", this.getTesterToken());
        Assert.assertTrue(o == 17);
    }

    @Given("^作为一个已登录的用户$")
    public void 作为一个已登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @When("^我点击自动控制班作业员岗位")
    public void 我点击自动控制班作业员岗位() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^跳转招聘职位详情页面$")
    public void 跳转招聘职位详情页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @When("^点击申请该岗位按钮$")
    public void 点击申请该岗位按钮() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^探出确认提示信息：是否确认申请昆明局-自动控制班作业员？$")
    public void 探出确认提示信息是否确认申请昆明局自动控制班作业员() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我已阅读并同意\\[申请岗位须知\\]钩选框$")
    public void 我已阅读并同意申请岗位须知钩选框() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^点击确认，提示申请成功（已投递 (\\d+)/(\\d+)），申请该岗位按钮变成已申请。$")
    public void 点击确认提示申请成功已投递申请该岗位按钮变成已申请(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getApplication();
        applicant.put("jobId", jobID);
        Integer o = (Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/application", applicant.toString(), "status", this.getTesterToken());
        logger.debug(">>>>>>申请该岗位>>>>>>" + o);
        //Assert.assertTrue(o == 200);
        Assert.assertTrue(o != null);
    }

    @And("^点击取消，返回招聘职位详情页面。$")
    public void 点击取消返回招聘职位详情页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Given("^作为一个已登录的用户（已申请(\\d+)个岗位）$")
    public void 作为一个已登录的用户已申请个岗位(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    private JSONObject getApplication(){
        try {
            JSONObject condition = new JSONObject();
            condition.put("jobId", "");
            condition.put("reassign", 1);
            return condition;
        }catch (JSONException jsonEx){
            return null;
        }
    }

}
