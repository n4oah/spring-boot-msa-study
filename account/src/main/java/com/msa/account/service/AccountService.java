package com.msa.account.service;

import com.msa.account.dto.SignupDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    void signup(SignupDto.SignupDtoReq request);
    boolean hasDuplicateAccount(String userId);
}
