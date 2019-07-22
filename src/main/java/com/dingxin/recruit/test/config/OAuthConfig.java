package com.dingxin.recruit.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.dingxin.recruit.test")
public class OAuthConfig {

    @Autowired
    private static OAuthConfig singleton = null;

    private String oAuthBasicAuth;
    private String loginURL;
    private String username;
    private String password;
    private String recType;
    private String authCode;
    private String codeCipher;

    public String getoAuthBasicAuth() {
        return oAuthBasicAuth;
    }

    public void setoAuthBasicAuth(String oAuthBasicAuth) {
        this.oAuthBasicAuth = oAuthBasicAuth;
    }

    public String getLoginURL() {
        return loginURL;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCodeCipher() {
        return codeCipher;
    }

    public void setCodeCipher(String codeCipher) {
        this.codeCipher = codeCipher;
    }

    @Autowired
    public void setSingleton(OAuthConfig config) {
        singleton = config;
    }

    public static OAuthConfig getSingleton() {
        return singleton;
    }
}
