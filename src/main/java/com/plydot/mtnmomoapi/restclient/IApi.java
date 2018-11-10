package com.plydot.mtnmomoapi.restclient;

import com.plydot.mtnmomoapi.model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by wilson on 9/25/2017.
 */

interface IApi {

    @POST("/disbursement/token/")
    Call<TokenResponse> getDisbToken();

    @POST("/v1_0/apiuser")
    Call<Void> createApiUser(@Body CreateApiUserBody createApiUserBody);

    @GET("/v1_0/apiuser/{X-Reference-Id}")
    Call<GetUserResponse> getApiUserInfo(@Path("X-Reference-Id") String XReferenceId);

    @POST("/v1_0/apiuser/{X-Reference-Id}/apikey")
    Call<GetApiKeyResponse> getApiKey(@Path("X-Reference-Id") String XReferenceId);

    @GET("/disbursement/v1_0/account/balance")
    Call<CheckBalanceResponse> checkAccountBalance();
}
