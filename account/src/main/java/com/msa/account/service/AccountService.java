package com.msa.account.service;

import com.msa.account.dto.SignupDto;

public interface AccountService {
    void signup(SignupDto.SignupDtoReq request);
    boolean hasDuplicateAccount(String userId);
}
