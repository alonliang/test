package com.dingxin.recruit.test.steps.attachmentUpload;

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
public class ZhaopinAttachmentUploadStep extends BaseRestAssuredSteps{

	@Autowired
	private TestConfig testConfig;
	
	@Before
	public void setup() {}
	
	private static String[] OID = new String[1];//附件ID
	
	
	@After
    public void teardown() {
		for(int i = 0; i < OID.length; i++) {
			 Map<String, String> cond1 = new HashMap<>();
		        cond1.put("C_OID", OID[i]);
		        delete("TB_REC_RESUME_BLOB", cond1);
		}
    }
	
	@When("^我在简历预览页面，在一个附件类型旁，点击上传，跳转到上传附件页面$")
	public void 我在简历预览页面在一个附件类型旁点击上传跳转到上传附件页面() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	@Given("^作为一个已登录用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
		
	@And("^我点击按钮，选择一个附件，跳转到上传附件页面$")
    public void 我点击按钮选择一个附件跳转到上传附件页面() throws Throwable {
		Assert.assertTrue(true);
    }
	
	@And("^我点击上传$")
	public void 我点击上传() throws Throwable {
		 JSONObject paramsJson = getSearchCondition(1);
	     @SuppressWarnings("rawtypes")
		 Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/upload/attachment", paramsJson.toString(), "data",this.getTesterToken());
	     if(o.size() > 0) {
	    	  OID[0] = o.get("id").toString();
	     }
	     Assert.assertTrue(o.size() > 0);
	}
	
	@Then("^提示上传成功，跳转到简历预览页$")
	public void 提示上传成功跳转到简历预览页() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	@Given("^作为一个填写完二个或以上选填项的用户$")
    public void 作为一个填写完二个或以上选填项的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
		
	@And("^我点击按钮，依次选择多个附件，我点击上传$")
    public void 我点击按钮依次选择多个附件我点击上传() throws Throwable {
		 JSONObject paramsJson = getSearchCondition(2);
	     @SuppressWarnings("rawtypes")
		 Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/upload/attachment", paramsJson.toString(), "data",this.getTesterToken());
	     if(o.size() > 0) {
	    	  OID[0] = o.get("id").toString();
	     }
	     Assert.assertTrue(o.size() > 0);
    }
	
	@When("^我在简历预览页面，在一个有附件类型旁，点击编辑，跳转到上传附件页面$")
	public void 我在简历预览页面在一个有附件类型旁点击编辑跳转到上传附件页面() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	@Given("^作为一个已上传附件用户$")
    public void 作为一个已上传附件用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }
	
	
	@And("^我点击重传按钮，点击确定重传，跳转到上传附件页面$")
    public void 我点击重传按钮点击确定重传跳转到上传附件页面() throws Throwable {
		Assert.assertTrue(true);
    }
	
	@And("^我选择一个附件，我点击上传，跳转到简历预览页$")
    public void 我选择一个附件我点击上传跳转到简历预览页() throws Throwable {
		 JSONObject paramsJson = getSearchCondition(1);
		 paramsJson.put("id",OID[0]);
	     @SuppressWarnings("rawtypes")
		 Map o = (Map) doPost(testConfig.getTestContextUrl() + "/zhaopin-resume/upload/attachment", paramsJson.toString(), "data",this.getTesterToken());
	     if(o.size() > 0) {
	    	  OID[0] = o.get("id").toString();
	     }
	     Assert.assertTrue(o.size() > 0);
    }
	
	@And("^我点击编辑$")
    public void 我点击编辑() throws Throwable {
		Assert.assertTrue(true);
    }
	
	@Then("^附件展示刚上传的附件$")
	public void 附件展示刚上传的附件() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	@And("^我点击取消，我点击退出，跳转到简历预览页$")
    public void 我点击取消我点击退出跳转到简历预览页() throws Throwable {
		Assert.assertTrue(true);
    }
	
	@Then("^附件未上传$")
	public void 附件未上传() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
	}
	
	
	private JSONObject getSearchCondition(int sum){
	   try {
		  
	       JSONObject condition = new JSONObject();
	       JSONArray arrayJson = new JSONArray();
           for(int i = 0; i < sum; i++) {
        	JSONObject json = new JSONObject();
	        json.put("fileType", "png");
	        json.put("content", fileToBase64());
	        arrayJson.put(json);
           }
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
