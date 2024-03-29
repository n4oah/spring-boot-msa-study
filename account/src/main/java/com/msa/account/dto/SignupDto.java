package com.msa.account.dto;

import com.msa.account.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SignupDto {
    public record SignupDtoReq(
            @NotNull
            @NotEmpty
            @Email
            String email,
            @NotEmpty
            @NotNull
            String password,
            @NotEmpty
            @NotNull
            String name
    ) {
        public Account toEntity(PasswordEncoder passwordEncoder) {
            Account account = Account
                .builder()
                    .email(this.email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .build();

            return account;
        }
    }
}
