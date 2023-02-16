package com.msa.account.dto;

import com.msa.account.domain.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

public class SignupDto {
    @RequiredArgsConstructor
    @Getter
    public static class SignupDtoReq {
        private final String userId;
        private final String password;
        private final String name;

        public Account toEntity(PasswordEncoder passwordEncoder) {
            Account account =
                    Account
                    .builder()
                        .userId(this.userId)
                        .password(passwordEncoder.encode(password))
                        .name(name).
//                        .authorities(new HashSet<>())
                    build();

            return account;
        }
    }
}
