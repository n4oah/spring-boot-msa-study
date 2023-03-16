package com.msa.account.exception;

public enum ExceptionCode {
    DUPLICATION("DUPLICATION"),
    VALIDATION_ERROR("VALIDATION_ERROR"),
    EXPIRED_JWT("EXPIRED_JWT");
    private String code;

    ExceptionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
