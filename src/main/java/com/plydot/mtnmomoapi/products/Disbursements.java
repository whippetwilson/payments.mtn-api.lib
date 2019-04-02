package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.model.TokenResponse;
import com.plydot.mtnmomoapi.model.TransferGetResponse;
import com.plydot.mtnmomoapi.model.TransferPostPayload;
import com.plydot.mtnmomoapi.model.collections.AccountBalance;
import com.plydot.mtnmomoapi.utils.PayeIDType;

import java.util.UUID;

public class Disbursements extends Collections implements IDisbursements {

    private BaseDisbursements disbursements;

    public Disbursements(String XReferenceId, String disbursementSubscriptionKey,
                         String callBackUrl, String enviroment) {
        super(XReferenceId, disbursementSubscriptionKey, callBackUrl, enviroment);
        this.disbursements = new BaseDisbursements(auth, enviroment);
    }

    @Override
    public AccountBalance getCollectionsBalance() {
        return disbursements.getAccountBalance();
    }

    @Override
    public TokenResponse getToken() {
        return disbursements.getToken();
    }

    @Override
    public TransferGetResponse transfer(String amount, String currency, String account, String message, PayeIDType payeIDType) {
        return disbursements.transfer(amount, currency, account, message, payeIDType, null, null);
    }

    @Override
    public TransferGetResponse transfer(String amount, String currency, String account, String message, PayeIDType payeIDType, UUID externalId) {
        return disbursements.transfer(amount, currency, account, message, payeIDType, externalId, null);
    }

    @Override
    public TransferGetResponse transfer(String amount, String currency, String account, String message, PayeIDType payeIDType, String XReferenceId) {
        return disbursements.transfer(amount, currency, account, message, payeIDType, null, XReferenceId);
    }

    @Override
    public TransferGetResponse transfer(String amount, String currency, String account, String message, PayeIDType payeIDType, UUID externalId, String XReferenceId) {
        return disbursements.transfer(amount, currency, account, message, payeIDType, externalId, XReferenceId);
    }

    @Override
    public TransferGetResponse getTransferStatus(String XReferenceId) {
        return disbursements.checkTransferStatus(XReferenceId);
    }
}
