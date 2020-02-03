package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.auth.Auth;
import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.model.collections.AccountBalance;
import com.plydot.mtnmomoapi.model.collections.Payer;
import com.plydot.mtnmomoapi.model.collections.Request2Pay;
import com.plydot.mtnmomoapi.model.collections.Request2PayStatus;
import com.plydot.mtnmomoapi.restclient.HttpRoutines;
import com.plydot.mtnmomoapi.restclient.Settings;
import com.plydot.mtnmomoapi.utils.PayeIDType;
import com.plydot.mtnmomoapi.utils.Status;

import java.util.UUID;

class BaseCollections {

    protected static Settings settings = Settings.getInstance();
    protected static HttpRoutines routines = new HttpRoutines();
    protected Auth auth;
    private TokenResponse token = null;

    BaseCollections(Auth auth, String enviroment, String prodApiKey) {
        this.auth = auth;
        settings.setEnviroment(enviroment);
        settings.setProductionApiKey(prodApiKey);
    }

    public Products getProduct() {
        return Products.COLLECTIONS;
    }

    public TokenResponse getToken() {
        try {
            TokenResponse response = auth.getToken(getProduct());
            if (response != null) {
                this.token = response;
                return this.token;
            } else {
                throw new IllegalArgumentException("Can't get access token, check your api credentials");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't get access token, check your api credentials");
        }
    }

    public Request2PayStatus request2Pay(String amount, String currency, String account,
                                         String message, PayeIDType payeIDType, UUID externalId, String XreferenceId) {
        if (isAccountActive(account, payeIDType).toString().equals(Status.OK.toString())) {
            String reference = getReference(XreferenceId);
            Request2Pay request2Pay =  getRequestBody(amount, currency, account, message, payeIDType, externalId, reference);
            setRequestSettings(reference);
            Request2Pay request2Pay1 = routines.collectionsRequest2Pay(request2Pay);
            if (request2Pay1.getStatus().equals(Status.ACCEPTED.toString())) {
                return routines.checkRequest2PayStatus(request2Pay1);
            } else {
                return new Request2PayStatus(request2Pay1.getStatus());
            }
        } else {
            throw new IllegalArgumentException(account + " is invalid");
        }
    }

    protected String getReference(String XreferenceId){
        String reference;
        if (XreferenceId == null) {
            reference = UUID.randomUUID().toString();
        } else {
            reference = XreferenceId;
        }
        return reference;
    }

    protected void setRequestSettings(String reference){
        settings.setAccessToken(getToken());
        settings.setxReferenceId(reference);
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
    }

    protected Request2Pay getRequestBody(String amount, String currency, String account,
                                         String message, PayeIDType payeIDType, UUID externalId, String reference){
        Request2Pay request2Pay = new Request2Pay();
        request2Pay.setAmount(amount);
        if (externalId != null) {
            request2Pay.setExternalId(externalId.toString());
        } else {
            request2Pay.setExternalId(auth.getXReferenceId());
        }
        request2Pay.setCurrency(currency);
        request2Pay.setPayeeNote(message);
        request2Pay.setPayerMessage(message);
        request2Pay.setXreferenceId(reference);
        Payer payer = new Payer();
        payer.setPartyIdType(payeIDType.toString());
        payer.setPartyId(account);
        request2Pay.setPayer(payer);
        return request2Pay;
    }

    public Request2PayStatus getRequest2PayStatus(String XReferenceId) {
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        return routines.checkRequest2PayStatus(XReferenceId);
    }

    public Status isAccountActive(String account, PayeIDType payeIDType) {
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        Status status = routines.isAccountActive(account, payeIDType, getProduct());
        if (status.toString().equals(Status.OK.toString())) {
            return Status.OK;
        } else {
            return Status.INVALID;
        }
    }

    public AccountBalance getAccountBalance() {
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        settings.setxReferenceId(null);
        routines.setSettings(settings);
        Products product = getProduct();
        switch (product){
            case COLLECTIONS:
                return routines.getAccountBalance(product);
            case DISBURSEMENTS:
                return routines.getAccountBalance(product);
            default:
                throw new NullPointerException("Unsupported product");
        }

    }
}
