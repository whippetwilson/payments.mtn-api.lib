package com.plydot.mtnmomoapi.model;

import com.plydot.mtnmomoapi.model.collections.Payer;
import com.plydot.mtnmomoapi.model.collections.Request2Pay;

public class TransferPostPayload extends Request2Pay {
    public Payer payee;

    public Payer getPayee() {
        return payee;
    }

    public void setPayee(Payer payee) {
        this.payee = payee;
    }
}
