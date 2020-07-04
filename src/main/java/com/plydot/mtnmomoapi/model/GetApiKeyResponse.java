
package com.plydot.mtnmomoapi.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GetApiKeyResponse {

    @Expose
    private String apiKey;

    private String status;

    public GetApiKeyResponse(String apiKey, String status) {
        this.apiKey = apiKey;
        this.status = status;
    }

    public GetApiKeyResponse(String status) {
        this.status = status;
    }

    public GetApiKeyResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
