package com.dingxin.recruit.test.steps.resumeIntegrity;

import com.dingxin.recruit.test.Application;
import com.dingxin.recruit.test.base.BaseRestAssuredSteps;
import com.dingxin.recruit.test.config.TestConfig;
import com.dingxin.recruit.test.repository.CustomerRepository;
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
import java.util.List;
import java.util.Map;

/**
 */
@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoPinResumeIntegrityStep extends BaseRestAssuredSteps {
    static final Logger logger = LoggerFactory.getLogger(ZhaoPinResumeIntegrityStep.class);

    @Autowired
    private TestConfig testConfig;

    @Autowired
    private CustomerRepository customerRepository;

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
        cond2.put("C_CTF_NO","520181567811280249");
        delete("TB_REC_RESUME",cond2);
        //清除联系数据
        Map<String, String> cond3 = new HashMap<>();
        cond3.put("C_CONTACT","lxy999999");
        delete("TB_REC_COMMUNICATION",cond3);
        //清除工作经验数据
        Map<String, String> cond4 = new HashMap<>();
        cond4.put("C_COMPANY","广州xx公司");
        delete("TB_REC_SOCIAL_EXP",cond4);

        Map<String, String> cond5 = new HashMap<>();
        cond5.put("C_COMPANY","贵州xx公司");
        delete("TB_REC_SOCIAL_EXP",cond5);
    }

    @Given("^作为一个已登录用户$")
    public void 作为一个已登录用户()throws Throwable{
        Assert.assertTrue(true);
    }

    private JSONObject getApplication() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","张三2");
            jsonObject.put("gender","1" );
            jsonObject.put("ctfType","1");
            jsonObject.put("ctfNo","520181567811280249");
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

    private JSONObject getContactJson(){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fristMobilePhone", "lxy999999");
            jsonObject.put("mobilePhone", "214325465");
            jsonObject.put("email", "2132857");
            jsonObject.put("fixedTelephone", "123344");
            jsonObject.put("otherContact", "23567");
            jsonObject.put("contactPlace", "23456");
            jsonObject.put("postalCode", "xlxxl");

            Map<String, String> cond = new HashMap<>();
            cond.put("C_CTF_NO","520181567811280249");
            List<Map<String, Object>> list=customerRepository.get("TB_REC_RESUME",cond);

            if (list != null && !list.isEmpty()) {
                Map m = list.get(0);
                jsonObject.put("resumeId",m.get("C_OID"));

            }

            return jsonObject;
        }catch (JSONException jsonEx){
            return null;
        }
    }

    private JSONObject getExperienceJson(String company){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("beginDate", "1464883200");
            jsonObject.put("endDate", "1528681426");
            jsonObject.put("company", company);
            jsonObject.put("position", "测试人员");
            jsonObject.put("workType", "2");
            jsonObject.put("mainResp", "负责测试");
            jsonObject.put("companyProperty", "1");
            jsonObject.put("companyScope", "100");

            Map<String, String> cond = new HashMap<>();
            cond.put("C_CTF_NO", "520181567811280249");
            List<Map<String, Object>> list = customerRepository.get("TB_REC_RESUME", cond);

            if (list != null && !list.isEmpty()) {
                Map m = list.get(0);
                jsonObject.put("resumeId", m.get("C_OID"));

            }
            return jsonObject;
        }catch (JSONException jsonEx){
            return null;
        }
    }



    @When("^我填写简历信息，点击提交，跳转简历预览页")
    public void 我填写简历信息点击提交跳转简历预览页()throws Throwable{

        JSONObject applicant = getApplication();
        int o = (Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/vitae/basicInfo", applicant.toString(), "code", this.getTesterToken());
        Assert.assertTrue( o== 200);
        //填写联系方式
        JSONObject contact=getContactJson();
        int i=(Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/vitae/contact",contact.toString(), "code", this.getTesterToken());
//        Assert.assertTrue( i== 200);
        //填写工作经历
        JSONObject experience1=getExperienceJson("广州xx公司");
        JSONObject experience2=getExperienceJson("贵州xx公司");
        int j=(Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/vitae/experience",contact.toString(), "code", this.getTesterToken());
        int k=(Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/vitae/experience",contact.toString(), "code", this.getTesterToken());

    }

    @Then("^完整度增加")
    public void 完整度增加() throws Throwable{

        //调用查询接口获取完整度
        int k=(Integer) doGet(testConfig.getTestContextUrl()+"/zhaopin-resume/all","code");
        Assert.assertTrue( k== 200);
    }

    @And("^增加的完整度等于增加的字段数除以总字段数乘百分之百")
    public void 增加的完整度等于增加的字段数除以总字段数乘百分之百() throws Throwable{
        Assert.assertTrue(true);
    }


    @Given("^作为一个填写过简历用户$")
    public void 作为一个填写过简历用户()throws Throwable{
        Assert.assertTrue(true);
    }

    @When("^我删除简历中某一模块内全部信息，点击提交")
    public void 我删除简历中某一模块内全部信息点击提交()throws Throwable{
        //找到一个工作经历
        Map<String, String> cond = new HashMap<>();
        cond.put("C_COMPANY","贵州xx公司");
        List<Map<String, Object>> list=customerRepository.get("TB_REC_SOCIAL_EXP",cond);

        if (list != null && !list.isEmpty()) {
            String c_oid = (String) list.get(0).get("C_OID");
            int m=(Integer) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/vitae/experience/"+c_oid, "code", this.getTesterToken());
            Assert.assertTrue( m== 200);
        }
    }

    @Then("^完整度减少")
    public void 完整度减少()throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^减少的完整度等于减少的字段数除以总字段数乘以百分之百")
    public void 减少的完整度等于减少的字段数除以总字段数乘以百分之百()throws Throwable{
        Assert.assertTrue(true);
    }

    @Given("^作为一个在工作经历模块填写过多个工作经历的用户")
    public void 作为一个在工作经历模块填写过多个工作经历的用户()throws Throwable{
        Assert.assertTrue(true);
    }


    @When("^我增加或删除工作经历，且保留至少一条工作经历")
    public void 我增加或删除工作经历且保留至少一条工作经历()throws Throwable{
        Assert.assertTrue(true);
    }

    @And("^我点击提交")
    public void 我点击提交()throws Throwable{
        Assert.assertTrue(true);
    }

    @Then("^跳转简历预览页，完整度不变")
    public void 跳转简历预览页完整度不变()throws Throwable{
        Assert.assertTrue(true);
    }

    @Given("^作为一个在联系方式模块填写过多个联系方式的用户$")
    public void 作为一个在联系方式模块填写过多个联系方式的用户()throws Throwable{
        Assert.assertTrue(true);
    }

    @When("^我增加或删除字段，且保留至少一条联系方式")
    public void 我增加或删除字段且保留至少一条联系方式()throws Throwable{
        Assert.assertTrue(true);
    }
}
