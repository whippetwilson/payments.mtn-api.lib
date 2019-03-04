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

    private static Settings settings = Settings.getInstance();
    private static HttpRoutines routines = new HttpRoutines();
    private Auth auth;
    private TokenResponse token = null;

    BaseCollections(Auth auth, String enviroment) {
        this.auth = auth;
        settings.setEnviroment(enviroment);
    }

    public TokenResponse getToken(){
        if (this.token != null){
            return this.token;
        }else {
            try {
                TokenResponse response = auth.getToken(Products.COLLECTIONS);
                if (response != null) {
                    this.token = response;
                    return getToken();
                } else {
                    throw new IllegalArgumentException("Can't get access token, check your api credentials");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Can't get access token, check your api credentials");
            }
        }
    }

    public Request2PayStatus request2Pay(String amount, String currency, String account,
                                   String message, PayeIDType payeIDType, UUID externalId){
        if (isAccountActive(account, payeIDType).toString().equals(Status.OK.toString())) {
            Request2Pay request2Pay = new Request2Pay();
            String reference = UUID.randomUUID().toString();
            request2Pay.setAmount(amount);
            if (externalId != null) {
                request2Pay.setExternalId(externalId.toString());
            }else {
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
            settings.setAccessToken(getToken());
            settings.setxReferenceId(reference);
            settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
            routines.setSettings(settings);
            Request2Pay request2Pay1 = routines.collectionsRequest2Pay(request2Pay);
            if (request2Pay1.getStatus().equals(Status.ACCEPTED.toString())) {
                return routines.checkRequest2PayStatus(request2Pay1);
            }else {
                return new Request2PayStatus(request2Pay1.getStatus());
            }
        }else {
            throw new IllegalArgumentException(account+" is invalid");
        }
    }

    public Request2PayStatus getRequest2PayStatus(String XReferenceId){
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        return routines.checkRequest2PayStatus(XReferenceId);
    }

    public Status isAccountActive(String account, PayeIDType payeIDType){
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        Status status = routines.isAccountActive(account, payeIDType);
        if (status.toString().equals(Status.OK.toString())) {
            return Status.OK;
        }else {
            return Status.INVALID;
        }
    }

    public AccountBalance getAccountBalance(){
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        settings.setxReferenceId(null);
        routines.setSettings(settings);
        return routines.getCollectionsAccountBalance();
    }
}
