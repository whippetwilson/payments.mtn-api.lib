package com.plydot.mtnmomoapi.restclient;

import com.plydot.mtnmomoapi.model.*;
import com.plydot.mtnmomoapi.model.collections.AccountBalance;
import com.plydot.mtnmomoapi.model.collections.Request2Pay;
import com.plydot.mtnmomoapi.model.collections.Request2PayStatus;
import com.plydot.mtnmomoapi.products.Products;
import com.plydot.mtnmomoapi.utils.PayeIDType;
import com.plydot.mtnmomoapi.utils.Status;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

public class HttpRoutines {


    private IApi service;
    private Settings settings;

    public HttpRoutines(Settings settings) {
        service = new HttpService(settings).service();
        this.settings = settings;
    }

    public HttpRoutines() {
    }

    public void setSettings(Settings settings) {
        service = new HttpService(settings).service();
        this.settings = settings;
    }

    private void checkResponse(Response response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException(response.message());
        }
    }

    public TokenResponse getToken(Products product) throws IllegalAccessException {
        try {
            Response<TokenResponse> response = null;
            switch (product) {
                case DISBURSEMENTS:
                    response = service.getDisbToken().execute();
                    break;
                case COLLECTIONS:
                    response = service.getCollectionToken().execute();
                    break;
                default:
                    throw new IllegalAccessException("INVALID PRODUCT ID");
            }
            checkResponse(response);
            //noinspection ConstantConditions
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUser() {
        try {
            Response<Void> response = service.createApiUser(this.settings.getCreateApiUserBody()).execute();
            checkResponse(response);
            return Status.CREATED.toString();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public GetUserResponse getUser() {
        try {
            Response<GetUserResponse> response = service.getApiUserInfo(this.settings.getxReferenceId()).execute();
            checkResponse(response);
            GetUserResponse userResponse = response.body();
            Objects.requireNonNull(userResponse).setStatus(Status.OK.toString());
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new GetUserResponse(e.getMessage());
        }
    }

    public GetApiKeyResponse getApiKey() {
        try {
            Response<GetApiKeyResponse> response = service.getApiKey(this.settings.getxReferenceId()).execute();
            checkResponse(response);
            Objects.requireNonNull(response.body()).setStatus(Status.OK.toString());
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new GetApiKeyResponse(e.getMessage());
        }
    }

    public Request2Pay collectionsRequest2Pay(Request2Pay request2Pay){
        try {
            Response<Void> response = service.request2Pay(request2Pay).execute();
            checkResponse(response);
            request2Pay.setStatus(Status.ACCEPTED.toString());
            return request2Pay;
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            request2Pay.setStatus(e.getMessage());
            return request2Pay;
        }
    }

    public Request2PayStatus checkRequest2PayStatus(Request2Pay request2Pay){
        try {
            Response<Request2PayStatus> response = service.checkRequest2Status(request2Pay.getXreferenceId()).execute();
            checkResponse(response);
            Request2PayStatus status = response.body();
            if (status != null) {
                status.setRequest2Pay(request2Pay);
                return status;
            }else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new Request2PayStatus(e.getMessage());
        }
    }

    public Request2PayStatus checkRequest2PayStatus(String XReferenceId){
        try {
            Response<Request2PayStatus> response = service.checkRequest2Status(XReferenceId).execute();
            checkResponse(response);
            Request2PayStatus status = response.body();
            if (status != null) {
                return status;
            }else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new Request2PayStatus(e.getMessage());
        }
    }

    public Status isAccountActive(String account, PayeIDType type, Products product){
        try {
            Response<Void> response;
            switch (product){
                case COLLECTIONS:
                    response = service.isCollectionsAccountHolderActive(type.toString(), account).execute();
                    break;
                case DISBURSEMENTS:
                    response = service.isDisbursementAccountHolderActive(type.toString(), account).execute();
                    break;
                default:
                    throw new NullPointerException("Unsupported Product");
            }
            checkResponse(response);
            return Status.OK;
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return Status.BAD_REQUEST;
        }
    }

    public AccountBalance getAccountBalance(Products product){
        try {
            Response<AccountBalance> response;
            switch (product){
                case COLLECTIONS:
                    response = service.getCollectionsAccountBalance().execute();
                    break;
                case DISBURSEMENTS:
                    response = service.getDisbursementAccountBalance().execute();
                    break;
                default:
                    throw new NullPointerException("Unsupported Product");
            }
            checkResponse(response);
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new AccountBalance(e.getMessage());
        }
    }

    public TransferPostPayload transferRequest(TransferPostPayload payload){
        try {
            Response<Void> response = service.transfer(payload).execute();
            checkResponse(response);
            payload.setStatus(Status.ACCEPTED.toString());
            return payload;
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            payload.setStatus(e.getMessage());
            return payload;
        }
    }

    public TransferGetResponse checkTransferStatus(String XReferenceId){
        try {
            Response<TransferGetResponse> response = service.checkTransferStatus(XReferenceId).execute();
            checkResponse(response);
            TransferGetResponse status = response.body();
            if (status != null) {
                return status;
            }else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new TransferGetResponse(e.getMessage());
        }
    }

    public TransferGetResponse checkTransferStatus(TransferPostPayload payload){
        try {
            Response<TransferGetResponse> response = service.checkTransferStatus(payload.getXreferenceId()).execute();
            checkResponse(response);
            TransferGetResponse status = response.body();
            if (status != null) {
                status.setTransferPostPayload(payload);
                return status;
            }else {
                throw new NullPointerException();
            }
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new TransferGetResponse(e.getMessage());
        }
    }
}
