package com.my.util;

public enum OrderStatus {
    NEW(0), CREATED(1), SUCCEED(2), FAILED_LESS_INVENTORY(401);
    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}