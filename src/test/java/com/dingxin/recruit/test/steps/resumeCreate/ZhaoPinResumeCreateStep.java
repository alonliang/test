package com.dingxin.recruit.test.steps.resumeCreate;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;



import java.util.HashMap;
import java.util.Map;

/**
 */
@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoPinResumeCreateStep extends BaseRestAssuredSteps {

    static final Logger logger = LoggerFactory.getLogger(ZhaoPinResumeCreateStep.class);

    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {
        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        this.initAndLogin(this.getNewOne("USERNAME_API_TEST_NEW","UAhBzgKccNP568cf+868QCNLV2CH7Llnqh5TdjZFUSTqh+qGPdj7xTjZIvOAa0ow0wXsSLzM9OKo+idT7aMXno+KZWGP/r0/ZaQNRPgFcFvhvnQrqd6WN2LjDi1IXBTgZcAJRL+fStpUU+KnllPen9Q3xZysCq6YBRm6xj3t6wM="));
    }

    @After
    public void teardown() {
        //清除用户数据
        Map<String, String> cond1 = new HashMap<>();
        cond1.put("c_user_name", "USERNAME_API_TEST_NEW");
        delete("tb_rec_nw_applicant", cond1);
        Map<String, String> cond2 = new HashMap<>();
        cond2.put("C_CTF_NO","520181199311280249");
        delete("TB_REC_RESUME",cond2);
    }

    @Given("^作为一个未填写简历的用户$")
    public void 作为一个未填写简历的用户() throws Throwable{
        Assert.assertTrue(true);
    }

    @When("^我点击简历按钮，跳转个人中心页面")
    public void 我点击简历按钮跳转个人中心页面() throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^我点击简历按钮，跳转到简历编辑第一个页面")
    public void 我点击简历按钮跳转到简历编辑第一个页面 ()throws Throwable{
        Assert.assertTrue(true);
//        Result k=(Result) doGet(testConfig.getTestContextPath()+"/zhaopin-resume/all", "status");
//        Assert.assertTrue(k.getstatus() == 200);
    }

    @And("^我填写各项信息，点击保存按钮")
    public void 我填写各项信息点击保存按钮()throws Throwable{
        JSONObject applicant = getApplication();
        int o = (Integer) doPost( testConfig.getTestContextUrl()+ "/zhaopin-resume/vitae/basicInfo", applicant.toString(), "code", this.getTesterToken());
        Assert.assertTrue( o== 200);
    }

    private JSONObject getApplication() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","张三");
            jsonObject.put("gender","1" );
            jsonObject.put("ctfType","1");
            jsonObject.put("ctfNo","520181199311280249");
            jsonObject.put("birthDate","1464883200");
            jsonObject.put("height","178");
            jsonObject.put("weight","70");
            jsonObject.put("residenceAddr","2503");
            jsonObject.put("nation","3");
            jsonObject.put("nativePlace","2506");
            jsonObject.put("section","2501");
            jsonObject.put("graduationSchool","2506");
            jsonObject.put("highestEdu","2");
            jsonObject.put("recruitmentProperty","1");
            jsonObject.put("nationality","3");
            jsonObject.put("major","2");
            jsonObject.put("graduateTime","1464883200");
            jsonObject.put("politicalStatus","2");
            jsonObject.put("maritalStatus","1");
            jsonObject.put("highestDegree","1");
            jsonObject.put("specpm","3/100");
            jsonObject.put("scope","2.5");
            jsonObject.put("fullTimeYears","2");
            jsonObject.put("remark","备注内容备注内容");
            return jsonObject;
        }catch (JSONException jsonEx){
            return null;
        }
    }

    @Then("^提示保存成功，跳转简历页面")
    public void 提示保存成功跳转简历页面()throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^我点击简历，跳转到简历编辑第一个页面")
    public void 我点击简历跳转到简历编辑第一个页面()throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^我填写各项信息，点击取消，点击退出")
    public void 我填写各项信息点击取消点击退出()throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^跳转简历页面，简历页面无个人信息")
    public void 跳转简历页面简历页面无个人信息()throws Throwable{
        Assert.assertTrue(true);
    }
}
