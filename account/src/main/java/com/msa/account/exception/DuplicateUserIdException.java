package com.msa.account.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DuplicateUserIdException extends RuntimeException {
    private final String userId;
}
