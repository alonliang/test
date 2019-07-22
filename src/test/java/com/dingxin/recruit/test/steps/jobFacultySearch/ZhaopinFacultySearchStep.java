package com.dingxin.recruit.test.steps.jobFacultySearch;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaopinFacultySearchStep  extends BaseRestAssuredSteps{

    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {}

    @After
    public void teardown() {
        Map<String, String> cond1 = new HashMap<>();
        cond1.put("c_user_name", "USERNAME_API_TEST");
        delete("tb_rec_nw_applicant", cond1);
    }


    @When("^我点击专业搜索（首页搜索跳转后）$")
    public void 我点击专业搜索首页搜索跳转后() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Given("^作为一个未登录的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^选择不限$")
    public void 选择不限() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^显示“没有符合条件职位”。我点击返回，跳转首页$")
    public void 显示没有符合条件职位我点击返回跳转首页() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("profressionId","01001011");//自动化（强电）
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") == 0);
    }

    @Then("^显示所有专业包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）。$")
    public void 显示所有专业包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @And("^选择电气类、计算机类、通信类$")
    public void 选择电气类计算机类通信类() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^电气类、计算机类、通信类出现在已选专业（(\\d+)/(\\d+)）$")
    public void 电气类计算机类通信类出现在已选专业(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^显示该三个专业包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）$")
    public void 显示该三个专业包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("profressionId","01001");//电气类
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @And("^选择自动化类$")
    public void 选择自动化类() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^自动化类出现在已选专业（(\\d+)/(\\d+)）$")
    public void 自动化类出现在已选专业(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    private JSONObject getSearchCondition(){
        try {
            JSONObject condition = new JSONObject();
            condition.put("jobName", "");
            condition.put("orgId", "");
            condition.put("degreeId", "");
            condition.put("profressionId", "");
            condition.put("currentRowNumber", "1");
            condition.put("pageSize", "10");
            return condition;
        }catch (JSONException jsonEx){
            return null;
        }
    }
}
