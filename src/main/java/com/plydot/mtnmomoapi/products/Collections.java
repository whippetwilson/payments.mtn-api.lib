package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.auth.Auth;
import com.plydot.mtnmomoapi.model.GetUserResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.model.collections.AccountBalance;
import com.plydot.mtnmomoapi.model.collections.Request2PayStatus;
import com.plydot.mtnmomoapi.utils.PayeIDType;

import java.io.NotActiveException;
import java.util.UUID;

public class Collections implements ICollections {
    protected final Auth auth;
    private BaseCollections collections;

    public Collections(String XReferenceId, String collectionsSubscriptionKey,
                       String callBackUrl, String enviroment, String apiKey) {
        auth = new Auth(callBackUrl, XReferenceId, collectionsSubscriptionKey);
        this.collections = new BaseCollections(auth, enviroment, apiKey);
    }

    @Override
    public String createUser() {
        return auth.createUser();
    }

    @Override
    public GetUserResponse getUser() {
        return auth.getUser();
    }

    @Override
    public Request2PayStatus makeCollectionRequest2Pay(String amount, String currency, String account, String message,
                                                       PayeIDType payeIDType, UUID externalId, String XreferenceId) throws NotActiveException {
        return collections.request2Pay(amount, currency, account, message, payeIDType, externalId, XreferenceId);
    }

    @Override
    public Request2PayStatus makeCollectionRequest2Pay(String amount, String currency, String account, String message,
                                                       PayeIDType payeIDType) throws NotActiveException {
        return collections.request2Pay(amount, currency, account, message, payeIDType, null, null);
    }

    @Override
    public Request2PayStatus makeCollectionRequest2Pay(String amount, String currency, String account, String message,
                                                       PayeIDType payeIDType, UUID externalId) throws NotActiveException {
        return collections.request2Pay(amount, currency, account, message, payeIDType, externalId, null);
    }

    @Override
    public Request2PayStatus makeCollectionRequest2Pay(String amount, String currency, String account, String message,
                                                       PayeIDType payeIDType, String XreferenceId) throws NotActiveException {
        return collections.request2Pay(amount, currency, account, message, payeIDType, null, XreferenceId);
    }

    @Override
    public Request2PayStatus checkRequest2PayStatus(String referenceId) throws NotActiveException {
        return collections.getRequest2PayStatus(referenceId);
    }

    @Override
    public AccountBalance getCollectionsBalance() {
        return collections.getAccountBalance();
    }

    @Override
    public TokenResponse getToken() {
        return collections.getToken();
    }
}
