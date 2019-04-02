package com.plydot.mtnmomoapi.utils;

public enum Status {
    OK("OK"),
    BAD_REQUEST("BAD_REQUEST"),
    CREATED("CREATED"),
    ACCEPTED("ACCEPTED"),
    INVALID("INVALID"),
    SUCCESSFUL("SUCCESSFUL"),
    PENDING("PENDING"),
    FAILED("FAILED"),
    REJECTED("REJECTED"),
    ONGOING("ONGOING"),
    PAYER_LIMIT_REACHED("PAYER_LIMIT_REACHED"),
    NOT_ENOUGH_FUNDS("NOT_ENOUGH_FUNDS");

    private final String text;

    Status(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
