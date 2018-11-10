package com.plydot.mtnmomoapi.auth;

import com.plydot.mtnmomoapi.model.CreateApiUserBody;
import com.plydot.mtnmomoapi.model.GetApiKeyResponse;
import com.plydot.mtnmomoapi.model.GetUserResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.restclient.HttpRoutines;
import com.plydot.mtnmomoapi.restclient.Settings;

public class Auth {
    private static Settings settings = Settings.getInstance();
    private static HttpRoutines routines = new HttpRoutines();
    public static String createUser(String XReferenceId, String primarySubscriptionKey, String callBackHost){
        settings.setxReferenceId(XReferenceId);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        settings.setCreateApiUserBody(new CreateApiUserBody(callBackHost));
        routines.setSettings(settings);
        return routines.createUser();
    }

    public static GetUserResponse getUser(String XReferenceId, String primarySubscriptionKey){
        settings.setxReferenceId(XReferenceId);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        routines.setSettings(settings);
        return routines.getUser();
    }

    public static GetApiKeyResponse getApiKey(String XReferenceId, String primarySubscriptionKey){
        settings.setxReferenceId(XReferenceId);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        routines.setSettings(settings);
        return routines.getApiKey();
    }

    public static TokenResponse getToken(String XReferenceId, String primarySubscriptionKey,
                                         String apiKey, int productId){
        settings.setxReferenceId(XReferenceId);
        settings.setApiKey(apiKey);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        routines.setSettings(settings);
        return routines.getToken(productId);
    }
}
