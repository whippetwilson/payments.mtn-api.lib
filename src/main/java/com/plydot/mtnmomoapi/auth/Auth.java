package com.plydot.mtnmomoapi.auth;

import com.plydot.mtnmomoapi.model.CreateApiUserBody;
import com.plydot.mtnmomoapi.model.GetApiKeyResponse;
import com.plydot.mtnmomoapi.model.GetUserResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.products.Enviroments;
import com.plydot.mtnmomoapi.products.Products;
import com.plydot.mtnmomoapi.restclient.HttpRoutines;
import com.plydot.mtnmomoapi.restclient.Settings;
import com.plydot.mtnmomoapi.utils.Status;

public class Auth {
    private Settings settings = Settings.getInstance();
    private static HttpRoutines routines = new HttpRoutines();
    private String callBackHost;
    private static Auth auth = new Auth();
    private String XReferenceId;
    private String primarySubscriptionKey;
    private GetApiKeyResponse apiKey = null;

    private Auth() {
    }

    public Auth(String callBackHost, String XReferenceId, String primarySubscriptionKey) {
        auth.callBackHost = callBackHost;
        this.callBackHost = callBackHost;
        this.XReferenceId = XReferenceId;
        this.primarySubscriptionKey = primarySubscriptionKey;
    }

    public String createUser() {
        this.settings.setxReferenceId(this.XReferenceId);
        this.settings.setPrimarySubscriptionKey(this.primarySubscriptionKey);
        this.settings.setCreateApiUserBody(new CreateApiUserBody(this.callBackHost));
        this.settings.setAccessToken(null);
        routines.setSettings(this.settings);
        return routines.createUser();
    }

    public GetUserResponse getUser() {
        settings.setxReferenceId(XReferenceId);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        settings.setAccessToken(null);
        routines.setSettings(settings);
        return routines.getUser();
    }

    private GetApiKeyResponse getApiKey() {
        settings.setxReferenceId(XReferenceId);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        settings.setAccessToken(null);
        routines.setSettings(settings);
        if (settings.getEnviroment().equals(Enviroments.PRODUCTION)) {
            return new GetApiKeyResponse(settings.getProductionApiKey(), Status.OK.toString());
        }
        else return routines.getApiKey();
    }

    public TokenResponse getToken(Products productId) throws IllegalAccessException {
        settings.setxReferenceId(XReferenceId);
        if (this.apiKey != null && this.apiKey.getStatus().equals(Status.OK.toString())) {
            settings.setApiKey(this.apiKey.getApiKey());
            settings.setPrimarySubscriptionKey(primarySubscriptionKey);
            settings.setAccessToken(null);
            routines.setSettings(settings);
            return routines.getToken(productId);
        } else {
            this.apiKey = getApiKey();
            if (this.apiKey.getStatus().equals(Status.OK.toString())) {
                return getToken(productId);
            } else {
                throw new IllegalArgumentException("API KEY NOT_FOUND, DID YOU INITIALIZE AN API USER?");
            }
        }
    }

    public String getXReferenceId() {
        return XReferenceId;
    }

    public void setXReferenceId(String XReferenceId) {
        this.XReferenceId = XReferenceId;
    }

    public String getPrimarySubscriptionKey() {
        return primarySubscriptionKey;
    }

    public void setPrimarySubscriptionKey(String primarySubscriptionKey) {
        this.primarySubscriptionKey = primarySubscriptionKey;
    }

    public String getCallBackHost() {
        return callBackHost;
    }

    public void setCallBackHost(String callBackHost) {
        this.callBackHost = callBackHost;
    }
}
