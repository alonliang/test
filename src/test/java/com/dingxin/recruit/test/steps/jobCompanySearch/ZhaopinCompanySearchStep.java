package com.dingxin.recruit.test.steps.jobCompanySearch;

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
public class ZhaopinCompanySearchStep  extends BaseRestAssuredSteps{

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

    @When("^我点击单位搜索（首页搜索跳转后）$")
    public void 我点击单位搜索首页搜索跳转后() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Given("^作为一个未登录的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^选择二级单位下列箭头$")
    public void 选择二级单位下列箭头() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^选中不限$")
    public void 选中不限() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^显示二级单位包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）。$")
    public void 显示二级单位包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","cdb4badab23044c987fcae3c5b3c7c00");//广州供电局有限公司
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @Then("^选择四级单位，显示“没有符合条件职位”。我点击返回，跳转首页。$")
    public void 选择四级单位显示没有符合条件职位我点击返回跳转首页() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","9172f3f6d2524a0c9775c77d44e2c98b");//湛江中心支公司
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") == 0);
    }



    @And("^选择三级单位$")
    public void 选择三级单位() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^三级单位出现在已选单位，显示三级单位包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）$")
    public void 三级单位出现在已选单位显示三级单位包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","01d02bfbcc6d40a8a38af939788e074c");//广州局
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
    }

    @And("^选择四级单位$")
    public void 选择四级单位() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^显示选中四级单位，显示四级单位包含的最新职位列表（按时间排序，显示最新(\\d+)条），往上滑动屏幕，显示更多职位（会显示“正在加载”）。$")
    public void 显示选中四级单位显示四级单位包含的最新职位列表按时间排序显示最新条往上滑动屏幕显示更多职位会显示正在加载(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject jobSearch = getSearchCondition();
        jobSearch.put("orgId","6add4902f9ff4e56b8a94e2ddc57d148");//广东电网潮州潮安供电局
        Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-job/job/search", jobSearch.toString(), "data");
        Assert.assertTrue((Integer)o.get("total") > 0);
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
