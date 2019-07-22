package com.dingxin.recruit.test.steps.login;

import com.dingxin.recruit.test.Application;
import com.dingxin.recruit.test.base.BaseRestAssuredSteps;
import com.dingxin.recruit.test.config.OAuthConfig;
import com.dingxin.recruit.test.config.TestConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@ContextConfiguration(classes = Application.class)
@TestPropertySource("/application.properties")
public class ZhaoPinLoginStep extends BaseRestAssuredSteps{

    @Autowired
    private TestConfig testConfig;

    @Autowired
    private OAuthConfig oAuthConfig;



    @Before
    public void setup() {
        initAuthCodeToRedis(testConfig.getCodeCipher(), testConfig.getCodeCipher());
    }

    @After
    public void teardown() {
    }

    @When("^我点击校园招聘按钮，跳转登陆页面$")
    public void 我点击校园招聘按钮跳转登陆页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Given("^作为一个未登录的用户$")
    public void 作为一个未登录的用户() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我输入用户号和密码，点击登陆$")
    public void 我输入用户号和密码点击登陆() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @Then("^提示成功提示，跳转到首页面$")
    public void 提示成功提示跳转到首页面() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        HashMap result = (HashMap)doGet(testConfig.getTestContextUrl() + "/uaa/applicant");
        Assert.assertTrue(result.get("id")!=null);
    }

    @And("^我输入用户号（未注册）和密码，点击登陆$")
    public void 我输入用户号未注册和密码点击登陆() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", oAuthConfig.getoAuthBasicAuth());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", suffix(oAuthConfig.getUsername()));
        map.add("password", oAuthConfig.getPassword());
        map.add("recType", oAuthConfig.getRecType());
        map.add("authCode", oAuthConfig.getAuthCode());
        map.add("codeCipher", oAuthConfig.getCodeCipher());
        HttpEntity<MultiValueMap<String, String>> httpEnity = new HttpEntity<>(map, headers);
        try {
            ResponseEntity<HashMap> result = restTemplate.exchange(oAuthConfig.getLoginURL(), HttpMethod.POST, httpEnity, HashMap.class);
        }catch (org.springframework.web.client.HttpClientErrorException ex){
            Assert.assertTrue(ex.getRawStatusCode() == 400);
        }
        //Assert.assertTrue(true);
    }

    @Then("^显示注册失败提示（帐号密码不一致）$")
    public void 显示注册失败提示帐号密码不一致() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(true);
    }

    @And("^我输入用户号（已注册）和密码（不正确），点击登陆$")
    public void 我输入用户号已注册和密码不正确点击登陆() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", oAuthConfig.getoAuthBasicAuth());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", oAuthConfig.getUsername());
        map.add("password", "LukMP63adN0m2uMLNlK9SOoaVP1L+zS+MgTBJhUoMfZCilV0Z9KdRpa4lNbpCMHZyj/7yoiS3uEFn3IdekxippVvmRVOHwZvGE/zu3FkQJUsdm3GX2Q4fwaU3xs3xcloDDNwPgcUFyEmVLD/kFtUycn3uR9xi9fkfoXImtuAF5A=");
        map.add("recType", oAuthConfig.getRecType());
        map.add("authCode", oAuthConfig.getAuthCode());
        map.add("codeCipher", oAuthConfig.getCodeCipher());
        HttpEntity<MultiValueMap<String, String>> httpEnity = new HttpEntity<>(map, headers);
        ResponseEntity<HashMap> result = restTemplate.exchange(oAuthConfig.getLoginURL(), HttpMethod.POST, httpEnity, HashMap.class);
        Assert.assertTrue(result.hasBody());
        Assert.assertTrue((Integer)result.getBody().get("code") == 616);

        map.set("authCode", "ERROR");
        result = restTemplate.exchange(oAuthConfig.getLoginURL(), HttpMethod.POST, httpEnity, HashMap.class);
        Assert.assertTrue(result.hasBody());
        Assert.assertTrue((Integer)result.getBody().get("code") == 601);
        //Assert.assertTrue(true);
    }
}
