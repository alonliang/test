package com.dingxin.recruit.test.steps.jobFirstPageSearch;


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
public class ZhaopinFirstPageSearchStep extends BaseRestAssuredSteps{

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

    @When("^我输入搜索关键字“”（不输入任何关键字），我点击搜索按钮$")
    public void 我输入搜索关键字不输入任何关键字我点击搜索按钮() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Given("^作为一个未登录的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^跳转职位列表，“没有符合条件职位”（没有满足搜索条件结果）。我点击返回，跳转首页$")
    public void 跳转职位列表没有符合条件职位没有满足搜索条件结果我点击返回跳转首页() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("jobName","电工XXX");
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") == 0);
    }

    @Then("^跳转职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）$")
    public void 跳转职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("jobName","");
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @When("^我输入搜索关键字“电工”，我点击搜索按钮$")
    public void 我输入搜索关键字电工我点击搜索按钮() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^跳转职位列表（按时间排序，显示最新(\\d+)条包含“电工”关键字职位），往上滑动屏幕，显示更多包含“电工”关键字职位（会显示“正在加载”）$")
    public void 跳转职位列表按时间排序显示最新条包含电工关键字职位往上滑动屏幕显示更多包含电工关键字职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("jobName","电工");
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @When("^我输入搜索关键字“电工XXX”，我点击搜索按钮$")
    public void 我输入搜索关键字电工XXX我点击搜索按钮() throws Throwable {
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
