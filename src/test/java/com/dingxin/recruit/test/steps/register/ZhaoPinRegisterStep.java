package com.dingxin.recruit.test.steps.register;

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
public class ZhaoPinRegisterStep extends BaseRestAssuredSteps{

    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {
    }

    @After
    public void teardown() {
        Map<String, String> cond1 = new HashMap<>();
        cond1.put("c_user_name", "USERNAME_API_TEST");
        delete("tb_rec_nw_applicant", cond1);
    }

    @Override
    public JSONObject getNewOne() {
        try {
            JSONObject applicant = new JSONObject();
            applicant.put("userIdCard", "IDCARD_API_TEST");
            applicant.put("userMbphone", "MOBILE_API_TEST");
            applicant.put("email", "EMAIL_API_TEST@qq.com");
            applicant.put("username", "USERNAME_API_TEST");
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

    @Given("^作为一个未登录的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^我点击“立即注册”按钮，提示注册成功并自动登陆系统（跳转首页）$")
    public void 我点击立即注册按钮提示注册成功并自动登陆系统跳转首页() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @When("^我点击校园招聘按钮，跳转登陆页面，我点击注册按钮，跳转注册页面$")
    public void 我点击校园招聘按钮跳转登陆页面我点击注册按钮跳转注册页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我填入身份证号、手机号、电子邮箱、应聘类型、密码、确认密码、验证问题、验证答案、验证码、我以阅读并同意\\[用户同意协议\\]钩选框$")
    public void 我填入身份证号手机号电子邮箱应聘类型密码确认密码验证问题验证答案验证码我以阅读并同意用户同意协议钩选框() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne();
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==200);
        }
        //Assert.assertTrue(true);
    }

    @And("^我填入身份证号（已注册过）、手机号、电子邮箱、应聘类型、密码、确认密码、验证问题、验证答案、验证码、我以阅读并同意\\[用户同意协议\\]钩选框$")
    public void 我填入身份证号已注册过手机号电子邮箱应聘类型密码确认密码验证问题验证答案验证码我以阅读并同意用户同意协议钩选框() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne();
        if(applicant!=null){
//            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
//            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
//            Assert.assertTrue(result==200);

            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==200);

            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            applicant.put("userIdCard", "IDCARD_API_TEST_PASSED");
            result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==603);

            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            applicant.put("userMbphone", "MOBILE_API_TEST_PASSED");
            result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==604);

            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            applicant.put("email", "EMAIL_API_TEST_PASSED@qq.com");
            result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==605);
        }

        //Assert.assertTrue(true);
    }

    @Then("^我点击“立即注册”按钮，注册失败提示（身份证/手机号/电子邮箱已注册）$")
    public void 我点击立即注册按钮注册失败提示身份证手机号电子邮箱已注册() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我填入身份证号（已注册过）、手机号、电子邮箱、应聘类型、密码、确认密码（不一致\\)、验证问题、验证答案、验证码、我以阅读并同意\\[用户同意协议\\]钩选框$")
    public void 我填入身份证号已注册过手机号电子邮箱应聘类型密码确认密码不一致验证问题验证答案验证码我以阅读并同意用户同意协议钩选框() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^我点击“立即注册”按钮，提示（密码不一致）$")
    public void 我点击立即注册按钮提示密码不一致() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我填入身份证号（已注册过）、手机号、电子邮箱、应聘类型、密码、确认密码、验证问题、验证答案、错误验证码、我以阅读并同意\\[用户同意协议\\]钩选框$")
    public void 我填入身份证号已注册过手机号电子邮箱应聘类型密码确认密码验证问题验证答案错误验证码我以阅读并同意用户同意协议钩选框() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        JSONObject applicant = getNewOne();
        if(applicant!=null){
            initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
            applicant.put("authCode", "ERROR");
            int result = (Integer) doPost(testConfig.getTestContextUrl() + "/uaa/register", applicant.toString(), "code");
            Assert.assertTrue(result==601);
        }
        //Assert.assertTrue(true);
    }

    @Then("^我点击“立即注册”按钮，注册失败提示（验证码不正确）$")
    public void 我点击立即注册按钮注册失败提示验证码不正确() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
}
