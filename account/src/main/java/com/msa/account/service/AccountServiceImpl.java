package com.msa.account.service;

import com.msa.account.domain.Account;
import com.msa.account.dto.SignupDto;
import com.msa.account.exception.DuplicateUserIdException;
import com.msa.account.repository.AccountRepository;
import com.msa.account.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void signup(SignupDto.SignupDtoReq signupDtoReq) {
        if (this.hasDuplicateAccount(signupDtoReq.email())) {
            throw new DuplicateUserIdException(signupDtoReq.email());
        }

        final Account account = signupDtoReq.toEntity(this.passwordEncoder);

        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasDuplicateAccount(String email) {
        return this.accountRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public UserVo loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserVo(
                account.getEmail(),
                account.getPassword(),
                account.getAuthorities().stream().map(a -> a.getName()).collect(Collectors.toList()),
                account.getName()
        );
    }
}
