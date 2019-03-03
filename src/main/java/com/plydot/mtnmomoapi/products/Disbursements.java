package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.auth.Auth;
import com.plydot.mtnmomoapi.model.CheckBalanceResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.restclient.HttpRoutines;
import com.plydot.mtnmomoapi.restclient.Settings;

public class Disbursements {

    private static Settings settings = Settings.getInstance();
    private static HttpRoutines routines = new HttpRoutines();
    private Auth auth;

    public Disbursements(String enviroment, Auth auth) {
        settings.setEnviroment(enviroment);
        this.auth = auth;
    }

    private TokenResponse getToken(){
        try {
            TokenResponse response = auth.getToken(Products.DISBURSEMENTS);
            if (response != null){
                return response;
            }else {
                throw new IllegalArgumentException("Can't get access token, check your api credentials");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't get access token, check your api credentials");
        }
    }

    public CheckBalanceResponse checkAccountBalance() {
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        return routines.checkAccountBalance();
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
