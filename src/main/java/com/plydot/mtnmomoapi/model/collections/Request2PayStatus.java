
package com.plydot.mtnmomoapi.model.collections;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Request2PayStatus {

    @Expose
    private Long amount;
    @Expose
    private String currency;
    @Expose
    private String externalId;
    @Expose
    private String financialTransactionId;
    @Expose
    private Payer payer;
    @Expose
    private String status;
    private Request2Pay request2Pay;
    private String error;

    public Request2PayStatus(String error) {
        this.error = error;
    }

    public Request2PayStatus() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Request2Pay getRequest2Pay() {
        return request2Pay;
    }

    public void setRequest2Pay(Request2Pay request2Pay) {
        this.request2Pay = request2Pay;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFinancialTransactionId() {
        return financialTransactionId;
    }

    public void setFinancialTransactionId(String financialTransactionId) {
        this.financialTransactionId = financialTransactionId;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
