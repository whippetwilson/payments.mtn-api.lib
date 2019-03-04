package com.plydot.mtnmomoapi.restclient;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.aaronhe.threetengson.ThreeTenGsonAdapter;
import org.threeten.bp.LocalDateTime;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wilson on 9/25/2017.
 */

class HttpService {

    private Settings settings;

    public HttpService(Settings settings) {
        this.settings = settings;
    }

    public IApi service() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = ThreeTenGsonAdapter.registerAll(builder)
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //use unsafe http client for testing
//        httpClient = getUnsafeOkHttpClient();
        //noinspection ConstantCondition

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Accept", "application/json");
            if (settings.getPrimarySubscriptionKey() != null)
                requestBuilder.addHeader(Headers.SUBSCRIPTION_KEY, settings.getPrimarySubscriptionKey());
            if (settings.getxReferenceId() != null){
                requestBuilder.addHeader(Headers.X_REFERENCE_ID, settings.getxReferenceId());
            }
            if (settings.getEnviroment() != null){
                requestBuilder.addHeader(Headers.X_TARGET_ENVIROMENT, settings.getEnviroment());
            }
            if (settings.getAccessToken() != null) {
                requestBuilder.addHeader(Headers.AUTHORIZATION, settings.getAccessToken().getAccessToken());
            }else if (settings.getAuthorization() != null)
                requestBuilder.addHeader(Headers.AUTHORIZATION, settings.getAuthorization());
            Request request = requestBuilder.build();
            System.out.println(new Gson().toJson(request));
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConnectConstants.DEFAULT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        return retrofit.create(IApi.class);
    }
}
