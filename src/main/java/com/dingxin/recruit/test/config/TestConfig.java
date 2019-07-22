package com.dingxin.recruit.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.dingxin.recruit.test")
public class TestConfig {
    @Autowired
    private static TestConfig singleton = null;

    String testUrl;
    String testPort;
    String testContextPath;
    String testDbUri;
    String testDatabaseName;
    String authCode;
    String codeCipher;

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public String getTestPort() {
        return testPort;
    }

    public void setTestPort(String testPort) {
        this.testPort = testPort;
    }

    public String getTestContextPath() {
        return testContextPath;
    }

    public void setTestContextPath(String testContextPath) {
        this.testContextPath = testContextPath;
    }

    public String getTestDbUri() {
        return testDbUri;
    }

    public void setTestDbUri(String testDbUri) {
        this.testDbUri = testDbUri;
    }

    public String getTestDatabaseName() {
        return testDatabaseName;
    }

    public void setTestDatabaseName(String testDatabaseName) {
        this.testDatabaseName = testDatabaseName;
    }

    public String getTestContextUrl() {
        return getTestUrl() + getTestContextPath();
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
    public void setSingleton(TestConfig config) {
        singleton = config;
    }

    public static TestConfig getSingleton() {
        return singleton;
    }
}
