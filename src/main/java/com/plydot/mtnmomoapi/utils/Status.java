package com.plydot.mtnmomoapi.utils;

public enum Status {
    OK("OK"),
    BAD_REQUEST("BAD_REQUEST"),
    CREATED("CREATED"),
    ACCEPTED("ACCEPTED"),
    INVALID("INVALID");

    private final String text;

    Status(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
