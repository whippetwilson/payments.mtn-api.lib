package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.model.CheckBalanceResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.restclient.HttpRoutines;
import com.plydot.mtnmomoapi.restclient.Settings;

public class Disbursements {

    private static Settings settings = Settings.getInstance();
    private static HttpRoutines routines = new HttpRoutines();

    public static CheckBalanceResponse checkAccountBalance(TokenResponse token, String primarySubscriptionKey,
                                                    String enviroment) {
        settings.setAccessToken(token);
        settings.setPrimarySubscriptionKey(primarySubscriptionKey);
        settings.setEnviroment(enviroment);
        routines.setSettings(settings);
        return routines.checkAccountBalance();
    }
}
