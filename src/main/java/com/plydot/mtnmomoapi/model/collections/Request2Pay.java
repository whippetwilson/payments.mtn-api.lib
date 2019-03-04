
package com.plydot.mtnmomoapi.model.collections;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.plydot.mtnmomoapi.utils.Status;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Request2Pay {

    @Expose
    private String amount;
    @Expose
    private String currency;
    @Expose
    private String externalId;
    @Expose
    private String payeeNote;
    @Expose
    private Payer payer;
    @Expose
    private String payerMessage;

    private String status;

    private String XreferenceId;

    public String getXreferenceId() {
        return XreferenceId;
    }

    public void setXreferenceId(String xreferenceId) {
        XreferenceId = xreferenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public String getPayeeNote() {
        return payeeNote;
    }

    public void setPayeeNote(String payeeNote) {
        this.payeeNote = payeeNote;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getPayerMessage() {
        return payerMessage;
    }

    public void setPayerMessage(String payerMessage) {
        this.payerMessage = payerMessage;
    }

}
