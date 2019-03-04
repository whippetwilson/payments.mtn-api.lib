package com.plydot.mtnmomoapi.utils;

public enum  PayeIDType {
    MSISDN("msisdn"),
    EMAIL("EMAIL"),
    PARTY_CODE("PARTY_CODE");

    private final String text;

    PayeIDType(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
