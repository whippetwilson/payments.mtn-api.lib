package com.plydot.mtnmomoapi.products;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plydot.mtnmomoapi.auth.Auth;
import com.plydot.mtnmomoapi.model.TransferGetResponse;
import com.plydot.mtnmomoapi.model.TransferPostPayload;
import com.plydot.mtnmomoapi.model.collections.Payer;
import com.plydot.mtnmomoapi.model.collections.Request2Pay;
import com.plydot.mtnmomoapi.model.collections.Request2PayStatus;
import com.plydot.mtnmomoapi.utils.PayeIDType;
import com.plydot.mtnmomoapi.utils.Status;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.UUID;

public class BaseDisbursements extends BaseCollections {

    BaseDisbursements(Auth auth, String enviroment) {
        super(auth, enviroment);
    }

    @Override
    public Products getProduct() {
        return Products.DISBURSEMENTS;
    }

    @Override
    public Request2PayStatus request2Pay(String amount, String currency, String account,
                                         String message, PayeIDType payeIDType, UUID externalId,
                                         String XreferenceId) {
        throw new NotImplementedException();
    }

    @Override
    public Request2PayStatus getRequest2PayStatus(String XReferenceId) {
        throw new NotImplementedException();
    }

    public TransferGetResponse transfer(String amount, String currency, String account,
                                        String message, PayeIDType payeIDType, UUID externalId,
                                        String XreferenceId) {
        if (isAccountActive(account, payeIDType).toString().equals(Status.OK.toString())) {
            String reference = getReference(XreferenceId);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.serializeNulls().create();
            TransferPostPayload payload =
                    gson.fromJson(gson.toJson(getRequestBody(amount, currency, account,
                            message, payeIDType, externalId, reference)),
                            TransferPostPayload.class);
            payload.setPayee(payload.getPayer());
            setRequestSettings(reference);
            TransferPostPayload postPayload = routines.transferRequest(payload);
            if (postPayload.getStatus().equals(Status.ACCEPTED.toString())) {
                return routines.checkTransferStatus(postPayload);
            } else {
                return new TransferGetResponse(postPayload.getStatus());
            }
        } else {
            throw new IllegalArgumentException(account + " is invalid");
        }
    }

    public TransferGetResponse checkTransferStatus(String XReferenceId) {
        settings.setAccessToken(getToken());
        settings.setPrimarySubscriptionKey(auth.getPrimarySubscriptionKey());
        routines.setSettings(settings);
        return routines.checkTransferStatus(XReferenceId);
    }
}
