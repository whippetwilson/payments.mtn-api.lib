package com.plydot.mtnmomoapi.restclient;

import com.plydot.mtnmomoapi.model.CheckBalanceResponse;
import com.plydot.mtnmomoapi.model.GetApiKeyResponse;
import com.plydot.mtnmomoapi.model.GetUserResponse;
import com.plydot.mtnmomoapi.model.TokenResponse;
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
        if (!response.isSuccessful())
            throw new IOException(response.message());
    }

    public TokenResponse getToken(int product){
        try {
            Response<TokenResponse> response = null;
            switch (product){
                case 1:
                     response = service.getDisbToken().execute();
            }
            checkResponse(response);
            //noinspection ConstantConditions
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUser(){
        try {
            Response<Void> response = service.createApiUser(this.settings.getCreateApiUserBody()).execute();
            checkResponse(response);
            return "Created";
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public GetUserResponse getUser(){
        try {
            Response<GetUserResponse> response = service.getApiUserInfo(this.settings.getxReferenceId()).execute();
            checkResponse(response);
            GetUserResponse userResponse = response.body();
            Objects.requireNonNull(userResponse).setStatus("OK");
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new GetUserResponse(e.getMessage());
        }
    }

    public GetApiKeyResponse getApiKey(){
        try {
            Response<GetApiKeyResponse> response = service.getApiKey(this.settings.getxReferenceId()).execute();
            checkResponse(response);
            Objects.requireNonNull(response.body()).setStatus("OK");
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new GetApiKeyResponse(e.getMessage());
        }
    }

    public CheckBalanceResponse checkAccountBalance(){
        try {
            Response<CheckBalanceResponse> response = service.checkAccountBalance().execute();
            checkResponse(response);
            Objects.requireNonNull(response.body()).setStatus("OK");
            return response.body();
        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return new CheckBalanceResponse(e.getMessage());
        }
    }
}
