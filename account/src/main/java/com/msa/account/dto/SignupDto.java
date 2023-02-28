package com.msa.account.dto;

import com.msa.account.domain.Account;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SignupDto {
    public record SignupDtoReq(
            @Nonnull
            @NotEmpty
            String userId,
            @NotEmpty
            @Nonnull
            String password,
            @NotEmpty
            @Nonnull
            String name
    ) {
        public Account toEntity(PasswordEncoder passwordEncoder) {
            Account account = Account
                .builder()
                    .userId(this.userId)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .build();

            return account;
        }
    }
}
