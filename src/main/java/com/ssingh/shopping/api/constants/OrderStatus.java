package com.ssingh.shopping.api.constants;

public enum OrderStatus {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
