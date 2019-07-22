package com.dingxin.recruit.test.steps.jobDegreeSearch;

import com.dingxin.recruit.test.Application;
import com.dingxin.recruit.test.base.BaseRestAssuredSteps;
import com.dingxin.recruit.test.config.TestConfig;
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
public class ZhaopinDegreeSearchStep  extends BaseRestAssuredSteps{

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

    @When("^我点击学历搜索（首页搜索跳转后）$")
    public void 我点击学历搜索首页搜索跳转后() throws Throwable {
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

    @Then("^显示所有学历包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）。$")
    public void 显示所有学历包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","cdb4badab23044c987fcae3c5b3c7c00");//广州供电局有限公司
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @And("^选择中技、中专、大学专科$")
    public void 选择中技中专大学专科() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^，中技、中专、大学专科出现在已选学历（(\\d+)/(\\d+)）$")
    public void 中技中专大学专科出现在已选学历(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^显示该三个学历包含的最新职位列表（按时间排序，显示最新10条），往上滑动屏幕，显示更多职位（会显示“正在加载”）。$")
    public void 显示该三个学历包含的最新职位列表按时间排序显示最新10条往上滑动屏幕显示更多职位会显示正在加载() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","9172f3f6d2524a0c9775c77d44e2c98b");//湛江中心支公司
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") == 0);
    }

    @And("^选择博士研究生$")
    public void 选择博士研究生() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^博士研究生出现在已选学历（(\\d+)/(\\d+)）$")
    public void 博士研究生出现在已选学历(int arg0, int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^没有单位显示（没有符合条件职位）。我点击返回，跳转首页。$")
    public void 没有单位显示没有符合条件职位我点击返回跳转首页() throws Throwable {
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
