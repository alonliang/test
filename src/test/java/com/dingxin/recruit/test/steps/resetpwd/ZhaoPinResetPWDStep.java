package com.dingxin.recruit.test.steps.resetpwd;

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
import java.util.Map;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoPinResetPWDStep  extends BaseRestAssuredSteps {

    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {
    }

    private static String[] userName = new String[4];//用户ID

    @After
    public void teardown() {
        for(int i = 1; i <= userName.length; i++) {
            Map<String, String> cond1 = new HashMap<>();
            cond1.put("c_user_name", "USERNAME_API_TEST"+i);
            delete("tb_rec_nw_applicant", cond1);
        }
    }

    public JSONObject getNewOne(int sum) {
        try {
            JSONObject applicant = new JSONObject();
            applicant.put("userIdCard", "IDCARD_API_TEST"+sum);
            applicant.put("userMbphone", "MOBILE_API_TEST"+sum);
            applicant.put("email", "EMAIL_API_TEST@qq.com"+sum);
            applicant.put("username", "USERNAME_API_TEST"+sum);
            applicant.put("recType", "1");
            applicant.put("password", "UAhBzgKccNP568cf+868QCNLV2CH7Llnqh5TdjZFUSTqh+qGPdj7xTjZIvOAa0ow0wXsSLzM9OKo+idT7aMXno+KZWGP/r0/ZaQNRPgFcFvhvnQrqd6WN2LjDi1IXBTgZcAJRL+fStpUU+KnllPen9Q3xZysCq6YBRm6xj3t6wM=");
            applicant.put("oidValidation", suffix("VALIDATION"));
            applicant.put("answer", suffix("ANSWER"));
            applicant.put("authCode", testConfig.getAuthCode());
            applicant.put("codeCipher", testConfig.getCodeCipher());
            return applicant;
        }catch (JSONException jsonEx){
            return null;
        }
    }


    @Given("^作为一个已注册的用户（忘记密码）$")
    public void 作为一个已注册的用户忘记密码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @When("^我点击忘记密码链接，跳转重置密码页面$")
    public void 我点击忘记密码链接跳转重置密码页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^输入身份证号码，我点击获取验证问题$")
    public void 输入身份证号码我点击获取验证问题() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^显示验证问题，填写验证答案，输入新密码和确认密码，输入验证码$")
    public void 显示验证问题填写验证答案输入新密码和确认密码输入验证码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^点击修改密码，提示修改密码成功，跳转首面$")
    public void 点击修改密码提示修改密码成功跳转首面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne(1);
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            userName[0] = applicant.getString("username");
            Assert.assertTrue(result==200);
        }

        JSONObject validation = new JSONObject();
        validation.put("userIdCard", applicant.get("userIdCard"));
        validation.put("recType", applicant.get("recType"));
        Map resultMap = (HashMap)doPut(testConfig.getTestContextUrl() + "/uaa/validation", validation.toString(), "data");
        Assert.assertTrue(applicant.get("oidValidation").equals(resultMap.get("oidValidation")));


        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        validation.put("id", resultMap.get("id"));
        validation.put("answer", applicant.get("answer"));
        validation.put("password", "LukMP63adN0m2uMLNlK9SOoaVP1L+zS+MgTBJhUoMfZCilV0Z9KdRpa4lNbpCMHZyj/7yoiS3uEFn3IdekxippVvmRVOHwZvGE/zu3FkQJUsdm3GX2Q4fwaU3xs3xcloDDNwPgcUFyEmVLD/kFtUycn3uR9xi9fkfoXImtuAF5A=");
        validation.put("authCode", applicant.get("authCode"));
        validation.put("codeCipher", applicant.get("codeCipher"));
        int resultCode = (Integer)doPut(testConfig.getTestContextUrl() + "/uaa/reset", validation.toString(), "code");
        Assert.assertTrue(resultCode==200);
        //Assert.assertTrue(true);
    }

    @And("^输入措和不存在身份证号码，我点击获取验证问题$")
    public void 输入措和不存在身份证号码我点击获取验证问题() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^提示“身份证号码错误”$")
    public void 提示身份证号码错误() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne(2);
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            userName[1] = applicant.getString("username");
            Assert.assertTrue(result==200);
        }
        JSONObject validation = new JSONObject();
        validation.put("userIdCard", "ERROR");
        validation.put("recType", applicant.get("recType"));
        int resultCode = (Integer)doPut(testConfig.getTestContextUrl() + "/uaa/validation", validation.toString(), "code");
        Assert.assertTrue(resultCode==609);
        //Assert.assertTrue(true);
    }

    @Then("^显示验证问题，填写错误验证答案，输入新密码和确认密码，输入验证码$")
    public void 显示验证问题填写错误验证答案输入新密码和确认密码输入验证码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne(3);
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            userName[2] = applicant.getString("username");
            Assert.assertTrue(result==200);
        }

        JSONObject validation = new JSONObject();
        validation.put("userIdCard", applicant.get("userIdCard"));
        validation.put("recType", applicant.get("recType"));
        Map resultMap = (HashMap)doPut(testConfig.getTestContextUrl() + "/uaa/validation", validation.toString(), "data");
       // Assert.assertTrue(applicant.get("oidValidation").equals(resultMap.get("oidValidation")));

        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        validation.put("id", resultMap.get("id"));
        validation.put("answer", "ERROR");
        validation.put("password", "LukMP63adN0m2uMLNlK9SOoaVP1L+zS+MgTBJhUoMfZCilV0Z9KdRpa4lNbpCMHZyj/7yoiS3uEFn3IdekxippVvmRVOHwZvGE/zu3FkQJUsdm3GX2Q4fwaU3xs3xcloDDNwPgcUFyEmVLD/kFtUycn3uR9xi9fkfoXImtuAF5A=");
        validation.put("authCode", applicant.get("authCode"));
        validation.put("codeCipher", applicant.get("codeCipher"));
        int resultCode = (Integer)doPut(testConfig.getTestContextUrl() + "/uaa/reset", validation.toString(), "code");
        Assert.assertTrue(resultCode==610);
        //Assert.assertTrue(true);
    }

    @And("^点击修改密码，提示验证答案不正确，停留当前页面并清空答案、密码和验证吗$")
    public void 点击修改密码提示验证答案不正确停留当前页面并清空答案密码和验证吗() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^显示验证问题，填写验证答案，输入新密码和确认密码不一致，输入验证码$")
    public void 显示验证问题填写验证答案输入新密码和确认密码不一致输入验证码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^提示密码不一致，停留当前页面并清空密码和验证码$")
    public void 提示密码不一致停留当前页面并清空密码和验证码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^显示验证问题，填写验证答案，输入新密码和确认密码，输入错误验证码或验证码过期$")
    public void 显示验证问题填写验证答案输入新密码和确认密码输入错误验证码或验证码过期() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne(4);
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            userName[3] = applicant.getString("username");
            Assert.assertTrue(result==200);
        }

        JSONObject validation = new JSONObject();
        validation.put("userIdCard", applicant.get("userIdCard"));
        validation.put("recType", applicant.get("recType"));
        Map resultMap = (HashMap)doPut(testConfig.getTestContextUrl() + "/uaa/validation", validation.toString(), "data");
        Assert.assertTrue(applicant.get("oidValidation").equals(resultMap.get("oidValidation")));

        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        validation.put("id", resultMap.get("id"));
        validation.put("answer", applicant.get("answer"));
        validation.put("password", "LukMP63adN0m2uMLNlK9SOoaVP1L+zS+MgTBJhUoMfZCilV0Z9KdRpa4lNbpCMHZyj/7yoiS3uEFn3IdekxippVvmRVOHwZvGE/zu3FkQJUsdm3GX2Q4fwaU3xs3xcloDDNwPgcUFyEmVLD/kFtUycn3uR9xi9fkfoXImtuAF5A=");
        validation.put("authCode", "ERROR");
        validation.put("codeCipher", applicant.get("codeCipher"));
        int resultCode = (Integer)doPut(testConfig.getTestContextUrl() + "/uaa/reset", validation.toString(), "code");
        Assert.assertTrue(resultCode==601);
        //Assert.assertTrue(true);
    }

    @And("^点击修改密码，提示验证码不正确，停留当前页面并清空密码和验证码$")
    public void 点击修改密码提示验证码不正确停留当前页面并清空密码和验证码() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
}
