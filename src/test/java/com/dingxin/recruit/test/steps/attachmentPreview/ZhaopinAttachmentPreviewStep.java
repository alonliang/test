package com.dingxin.recruit.test.steps.attachmentPreview;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.dingxin.recruit.test.Application;
import com.dingxin.recruit.test.base.BaseRestAssuredSteps;
import com.dingxin.recruit.test.config.TestConfig;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaopinAttachmentPreviewStep extends BaseRestAssuredSteps{
	
	
    @Autowired
    private TestConfig testConfig;

    @Before
    public void setup() {
        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
        this.initAndLogin(this.getNewOne("USERNAME_API_TEST_NEW","UAhBzgKccNP568cf+868QCNLV2CH7Llnqh5TdjZFUSTqh+qGPdj7xTjZIvOAa0ow0wXsSLzM9OKo+idT7aMXno+KZWGP/r0/ZaQNRPgFcFvhvnQrqd6WN2LjDi1IXBTgZcAJRL+fStpUU+KnllPen9Q3xZysCq6YBRm6xj3t6wM="));
    }
	
	private static String OID = "9105f234f0604519b8beb0f02358306a";//附件ID
	
	@After
    public void teardown() {
        Map<String, String> cond1 = new HashMap<>();
        cond1.put("C_OID", OID);
        delete("TB_REC_RESUME_BLOB", cond1);
    }
	

	@When("^我在简历预览页面在一个有附件类型旁点击编辑$")
	public void 我在简历预览页面在一个有附件类型旁() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	@Given("^作为一个已登录用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
		
	@And("^跳转到上传附件页面$")
    public void 跳转到上传附件页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	@And("^点击上传$")
	public void 点击上传() throws Throwable {
		  JSONObject paramsJson = getSearchCondition();
	      @SuppressWarnings("rawtypes")
		  Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/upload/attachment", paramsJson.toString(), "data",this.getTesterToken());
	      if(o.size() > 0) {
	    	  OID = o.get("id").toString();
	      }
	      Assert.assertTrue(o.size() > 0);
	}
	
	@Then("^跳转到上传附件页面，展示附件$")
	public void 跳转到上传附件页面展示附件() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	@When("^我在简历中心页面，在学籍验证报告旁，点击右侧编辑/预览$")
	public void 我在简历中心页面在学籍验证报告旁点击右侧编辑预览() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	@Then("^跳转到上传附件页面，预览附件$")
	public void 跳转到上传附件页面预览附件() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Integer code = (Integer) doGet(testConfig.getTestContextUrl()+"/zhaopin-resume/list/attachment/ad723c3be6ba42d8a01bcabaf7af30b9","code");
        Assert.assertTrue( code == 200);
	}
	
	private JSONObject getSearchCondition(){
	   try {
	       JSONObject condition = new JSONObject();
	       JSONArray arrayJson = new JSONArray();
	       JSONObject json = new JSONObject();
	        json.put("fileType", "png");
	        json.put("content", fileToBase64());
	        arrayJson.put(json);
	       
            condition.put("id", "");
            condition.put("resumeId", "ad723c3be6ba42d8a01bcabaf7af30b9"); //简历ID
            condition.put("attachmentType", "4"); //附件类型
            condition.put("attachment", arrayJson);
            System.err.println(condition);
            return condition;
	    }catch (JSONException jsonEx){
	        return null;
	    }
	}
	
	public static String fileToBase64() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/base64/test.png");
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            throw new RuntimeException("文件路径无效\n" + e.getMessage());
        }
	}
	

	
}
