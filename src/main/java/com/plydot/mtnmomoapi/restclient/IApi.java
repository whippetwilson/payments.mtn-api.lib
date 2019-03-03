package com.plydot.mtnmomoapi.restclient;

import com.plydot.mtnmomoapi.model.*;
import com.plydot.mtnmomoapi.model.collections.Request2Pay;
import com.plydot.mtnmomoapi.model.collections.Request2PayStatus;
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

    @POST("/collection/token/")
    Call<TokenResponse> getCollectionToken();

    @POST("/v1_0/apiuser")
    Call<Void> createApiUser(@Body CreateApiUserBody createApiUserBody);

    @GET("/v1_0/apiuser/{X-Reference-Id}")
    Call<GetUserResponse> getApiUserInfo(@Path("X-Reference-Id") String XReferenceId);

    @POST("/v1_0/apiuser/{X-Reference-Id}/apikey")
    Call<GetApiKeyResponse> getApiKey(@Path("X-Reference-Id") String XReferenceId);

    @GET("/disbursement/v1_0/account/balance")
    Call<CheckBalanceResponse> checkAccountBalance();

    @POST("/collection/v1_0/requesttopay")
    Call<Void> request2Pay(@Body Request2Pay request2Pay);

    @GET("/collection/v1_0/requesttopay/{reference}")
    Call<Request2PayStatus> checkRequest2Status(@Path("reference") String reference);

    @GET("/collection/v1_0/accountholder/{accountHolderIdType}/{accountHolderId}/active")
    Call<Void> isAccountHolderActive(@Path("accountHolderIdType") String accountHolderIdType, @Path("accountHolderId") String accountHolderId);
}
