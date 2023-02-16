package com.msa.account.service;

import com.msa.account.domain.Account;
import com.msa.account.dto.SignupDto;
import com.msa.account.exception.DuplicateUserIdException;
import com.msa.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void signup(SignupDto.SignupDtoReq signupDtoReq) {
        if (this.hasDuplicateAccount(signupDtoReq.getUserId())) {
            throw new DuplicateUserIdException(signupDtoReq.getUserId());
        }

        final Account account = signupDtoReq.toEntity(this.passwordEncoder);

        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasDuplicateAccount(String userId) {
        return this.accountRepository.findAccountByUserId(userId).isPresent();
    }
}
