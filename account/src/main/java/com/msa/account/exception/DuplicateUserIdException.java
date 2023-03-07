package com.msa.account.exception;

public class DuplicateUserIdException extends ParentException {
    public DuplicateUserIdException(String userId) {
        super(ExceptionCode.DUPLICATION, "중복되는 유저 아이디입니다. email=" + userId);
    }
}
