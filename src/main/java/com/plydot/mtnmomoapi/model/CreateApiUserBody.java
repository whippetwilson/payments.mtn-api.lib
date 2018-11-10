
package com.plydot.mtnmomoapi.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CreateApiUserBody {

    @Expose
    private String providerCallbackHost;

    public String getProviderCallbackHost() {
        return providerCallbackHost;
    }

    public void setProviderCallbackHost(String providerCallbackHost) {
        this.providerCallbackHost = providerCallbackHost;
    }

    public CreateApiUserBody(String providerCallbackHost) {
        this.providerCallbackHost = providerCallbackHost;
    }
}
