
package com.plydot.mtnmomoapi.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.plydot.mtnmomoapi.model.collections.Payer;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class TransferGetResponse {

    @Expose
    private Long amount;
    @Expose
    private String currency;
    @Expose
    private String externalId;
    @Expose
    private Payer payee;
    @Expose
    private Reason reason;
    @Expose
    private String status;

    private String error;

    private TransferPostPayload transferPostPayload;

    public TransferPostPayload getTransferPostPayload() {
        return transferPostPayload;
    }

    public void setTransferPostPayload(TransferPostPayload transferPostPayload) {
        this.transferPostPayload = transferPostPayload;
    }

    public TransferGetResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public Payer getPayee() {
        return payee;
    }

    public void setPayee(Payer payee) {
        this.payee = payee;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
