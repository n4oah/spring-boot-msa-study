package com.msa.account.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ParentException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public ParentException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
