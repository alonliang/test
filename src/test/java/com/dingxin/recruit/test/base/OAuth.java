package com.dingxin.recruit.test.base;

import java.util.HashMap;

import com.dingxin.recruit.test.config.OAuthConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;

@Component
public class OAuth {

    static final Logger logger = LoggerFactory.getLogger(OAuth.class);

    private static OAuthConfig oAuthConfig;

    @Autowired
    public void setOAuthConfig(OAuthConfig test) {
        OAuth.oAuthConfig = test;
    }

    @Autowired
    private static RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate test) {
        OAuth.redisTemplate = test;
    }

    private static String token;

    /*private static String oAuthBasicAuth = "Basic YXBwOmFwcA==";
    private static String loginURL = "http://127.0.0.1:3000/uaa/oauth/token";
    private static String username = "837601942@qq.com";
    private static String password = "abcde12345";
    private static String recType = "1";
    private static String authCode = "BPIF";
    private static String codeCipher = "c65bf5608aeea9640c4a906c90638b50";*/

    public static boolean loginOAtuh() {
        ValueOperations<String, String> operations=redisTemplate.opsForValue();
        operations.set("auth_code_token:" + oAuthConfig.getCodeCipher(), oAuthConfig.getCodeCipher());


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", oAuthConfig.getoAuthBasicAuth());

        /*MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", username);
        map.add("password", password);
        map.add("recType", recType);
        map.add("authCode", authCode);
        map.add("codeCipher", codeCipher);

        HttpEntity<MultiValueMap<String, String>> httpEnity = new HttpEntity<>(map, headers);
        ResponseEntity<HashMap> result = restTemplate.exchange(loginURL, HttpMethod.POST, httpEnity, HashMap.class);*/

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", oAuthConfig.getUsername());
        map.add("password", oAuthConfig.getPassword());
        map.add("recType", oAuthConfig.getRecType());
        map.add("authCode", oAuthConfig.getAuthCode());
        map.add("codeCipher", oAuthConfig.getCodeCipher());

        HttpEntity<MultiValueMap<String, String>> httpEnity = new HttpEntity<>(map, headers);
        ResponseEntity<HashMap> result = restTemplate.exchange(oAuthConfig.getLoginURL(), HttpMethod.POST, httpEnity, HashMap.class);

        if (result.hasBody()) {
            HashMap<String, Object> response = result.getBody();
            token = response.get("access_token").toString();
            return (!Strings.isNullOrEmpty(token));
        }
        return false;
    }

    public static String getHeaderToken() {
        System.out.println(token);
        return "bearer " + token;
    }

    public static String getUrlToken() {
        System.out.println(token);
        return "?access_token=" + token;
    }
}
