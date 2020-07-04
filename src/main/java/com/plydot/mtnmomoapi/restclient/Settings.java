package com.plydot.mtnmomoapi.restclient;

import com.plydot.mtnmomoapi.utils.Base64Utils;
import com.plydot.mtnmomoapi.model.CreateApiUserBody;
import com.plydot.mtnmomoapi.model.TokenResponse;

public final class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    private String primarySubscriptionKey;
    private String secondarySubscriptionKey;
    private String userId;
    private String apiKey;
    private String xReferenceId;
    private CreateApiUserBody createApiUserBody;
    private TokenResponse accessToken;
    private String enviroment;
    private String currency;
    private String productionApiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPrimarySubscriptionKey() {
        return primarySubscriptionKey;
    }

    public void setPrimarySubscriptionKey(String primarySubscriptionKey) {
        this.primarySubscriptionKey = primarySubscriptionKey;
    }

    public String getSecondarySubscriptionKey() {
        return secondarySubscriptionKey;
    }

    public void setSecondarySubscriptionKey(String secondarySubscriptionKey) {
        this.secondarySubscriptionKey = secondarySubscriptionKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorization() {
        if (this.xReferenceId == null || this.apiKey == null)
            return null;
        else
            return String.format("Basic %s", Base64Utils.encodeToString(String.format("%s:%s", this.xReferenceId, this.apiKey).getBytes()));
    }

    public String getxReferenceId() {
        return xReferenceId;
    }

    public void setxReferenceId(String xReferenceId) {
        this.xReferenceId = xReferenceId;
    }

    public CreateApiUserBody getCreateApiUserBody() {
        return createApiUserBody;
    }

    public void setCreateApiUserBody(CreateApiUserBody createApiUserBody) {
        this.createApiUserBody = createApiUserBody;
    }

    public TokenResponse getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(TokenResponse accessToken) {
        this.accessToken = accessToken;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProductionApiKey() {
        return productionApiKey;
    }

    public void setProductionApiKey(String productionApiKey) {
        this.productionApiKey = productionApiKey;
    }
}
