package com.example.googleads_test_app.googleads_oauth_creds_gen.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthConfig {

    @Value("${google.ads.clientId}")
    private String clientId;

    @Value("${google.ads.clientSecret}")
    private String clientSecret;

    @Value("${google.ads.developerToken}")
    private String developerToken;

    @Value("${google.ads.loginCustomerId}")
    private String loginCustomerId;

    @Value("${google.ads.loginEmailAddressHint}")
    private String loginEmailAddressHint;

    @Value("${google.ads.redirectUri}")
    private String redirectUri;

    private String authorizationCode;

    private String state;

    private String scope;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getDeveloperToken() {
        return developerToken;
    }

    public void setDeveloperToken(String developerToken) {
        this.developerToken = developerToken;
    }

    public String getLoginCustomerId() {
        return loginCustomerId;
    }

    public void setLoginCustomerId(String loginCustomerId) {
        this.loginCustomerId = loginCustomerId;
    }

    public String getLoginEmailAddressHint() {
        return loginEmailAddressHint;
    }

    public void setLoginEmailAddressHint(String loginEmailAddressHint) {
        this.loginEmailAddressHint = loginEmailAddressHint;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "OAuthConfig{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", developerToken='" + developerToken + '\'' +
                ", loginCustomerId='" + loginCustomerId + '\'' +
                ", loginEmailAddressHint='" + loginEmailAddressHint + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", state='" + state + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }

}
